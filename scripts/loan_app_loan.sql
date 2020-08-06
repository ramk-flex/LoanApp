SET SQL_SAFE_UPDATES = 0;

drop table if exists loan_app_borrower_address;
drop table if exists loan_app_borrower;
drop table if exists loan_app_property;
drop table if exists loan_app_loan;
drop table if exists loan_app_sequence_info;
drop table if exists loan_app_address;


create table loan_app_loan(
        id bigint(20) AUTO_INCREMENT primary key,
        version bigint(20),
        created_date_time datetime,
        created_by_user_id varchar(20),
        modified_date_time datetime,
        modified_by_user_id varchar(20),
        loan_number varchar(10),
        mortgage_type varchar(10),
        fha_case_number varchar(10),
        loan_amount decimal(15,2),
        loan_term smallint(5),
        product_type varchar(5)
);

CREATE TABLE loan_app_sequence_info (
        id bigint(20) NOT NULL AUTO_INCREMENT,
        version bigint(20) NOT NULL DEFAULT '0',
        created_date_time datetime NOT NULL,
        created_by_user_id varchar(20),
        modified_date_time datetime,
        modified_by_user_id varchar(20),
        effective_date_time datetime,
        expiration_date_time datetime,
        sequence_name varchar(100),
        sequence_number bigint(20),
        sequence_format varchar(10),
        PRIMARY KEY (id)
);

create table loan_app_borrower(
        id bigint(20) primary key auto_increment,
        loan_app_loan_id bigint(20),
        version bigint(20),
        created_date_time datetime,
        created_by_user_id varchar(20),
        modified_date_time datetime,
        modified_by_user_id varchar(20),
        borrower_number smallint(5),
        borrower_role_id varchar(25),
        first_name varchar(100),
        middle_name varchar(100),
        last_name varchar(100),
        social_security_number varchar(11),
        home_phone_number varchar(12),
        date_of_birth date,
        years_at_school smallint(2),
        marital_status varchar(20),
        number_of_dependents smallint(2),
FOREIGN KEY (loan_app_loan_id)
        REFERENCES loan_app_loan(id)
        ON DELETE CASCADE  ON UPDATE CASCADE
) ENGINE=InnoDB;


CREATE TABLE loan_app_address (
        id bigint(20) NOT NULL AUTO_INCREMENT,
        version bigint(20),
        created_date_time datetime,
        created_by_user_id varchar(20),
        modified_date_time datetime,
        modified_by_user_id varchar(20),
        address_line_one varchar(60) DEFAULT NULL,
        address_line_two varchar(60) DEFAULT NULL,
        city varchar(60) DEFAULT NULL,
        state_id varchar(2) DEFAULT NULL,
        postal_code_id varchar(10) DEFAULT NULL,
        state_fips_id varchar(10) DEFAULT NULL,
        county_fips_id varchar(10) DEFAULT NULL,
        county_id varchar(50) DEFAULT NULL,
        country_id varchar(50) DEFAULT NULL,
        PRIMARY KEY (id),
        KEY address_idx_state (state_id)
) ENGINE=InnoDB;



CREATE TABLE loan_app_property(
        id bigint(20) NOT NULL AUTO_INCREMENT,
        loan_app_loan_id bigint(20),
        property_address_id bigint(20),
        version bigint(20),
        created_date_time datetime,
        created_by_user_id varchar(20),
        modified_date_time datetime,
        modified_by_user_id varchar(20),
        occupancy_type_id varchar(20),
        number_of_floors smallint(5),
        number_of_units smallint(5),
        square_foot_age smallint(4),
        family_units smallint(4),
        month_built smallint(2),
        year_built  smallint(4),
        lot_size	smallint(10),
        purchase_price_amount decimal(10,2),
        new_construction_yn bit(1),
        PRIMARY KEY (id),
FOREIGN KEY (loan_app_loan_id)
        REFERENCES loan_app_loan(id)
        ON DELETE CASCADE  ON UPDATE CASCADE,
FOREIGN KEY (property_address_id)
        REFERENCES loan_app_address(id)
        ON DELETE CASCADE  ON UPDATE CASCADE
)ENGINE=InnoDB;

CREATE TABLE loan_app_borrower_address(
        id bigint(20) primary key auto_increment,
        loan_app_borrower_id bigint(20),
        loan_app_address_id bigint(20),
        version bigint(20),
        created_date_time datetime,
        created_by_user_id varchar(20),
        modified_date_time datetime,
        modified_by_user_id varchar(20),
        address_type varchar(20),
        no_of_years_stayed smallint(5),
        no_of_months_stayed smallint(5),
FOREIGN KEY (loan_app_borrower_id)
        REFERENCES loan_app_borrower(id)
        ON DELETE CASCADE  ON UPDATE CASCADE,
FOREIGN KEY (loan_app_address_id)
        REFERENCES loan_app_address(id)
        ON DELETE CASCADE  ON UPDATE CASCADE
)ENGINE=InnoDB;


delete from loan_app_sequence_info where sequence_name = 'LOAN_NUMBER';

INSERT INTO loanapp.loan_app_sequence_info
(id,version,created_date_time,created_by_user_id,modified_date_time,modified_by_user_id,effective_date_time,
expiration_date_time,sequence_name,sequence_number,sequence_format)VALUES(1,0,now(),user(),now(),user(),null,
null,'LOAN_NUMBER','1000000000',null);


DELIMITER $$;

$$;

DROP TRIGGER IF EXISTS tri_loan_app_loan;

$$;

CREATE TRIGGER tri_loan_app_loan BEFORE INSERT
ON loan_app_loan

FOR EACH ROW

BEGIN
	IF(NEW.version IS NULL) THEN
		SET NEW.version  = 0;
	END IF;

    IF(( NEW.created_date_time IS NULL) OR (TO_SECONDS(NEW.created_date_time) IS NULL)) THEN
		SET NEW.created_date_time = CURRENT_TIMESTAMP;
    END IF;

    IF( (NEW.created_by_user_id IS NULL) OR (NEW.created_by_user_id = ' ')) THEN
		SET NEW.created_by_user_id = CURRENT_USER;
    END IF;

    IF(( NEW.modified_date_time IS NULL) OR (TO_SECONDS(NEW.modified_date_time) IS NULL)) THEN
		SET NEW.modified_date_time = CURRENT_TIMESTAMP;
    END IF;

    IF( (NEW.modified_by_user_id IS NULL) OR (NEW.modified_by_user_id = ' ')) THEN
		SET NEW.modified_by_user_id = CURRENT_USER;
    END IF;

END;

$$;

DELIMITER $$;
$$;

DROP TRIGGER IF EXISTS tru_loan_app_loan;

$$;

CREATE TRIGGER tru_loan_app_loan BEFORE UPDATE
ON loan_app_loan

FOR EACH ROW
BEGIN
	IF(NEW.VERSION IS NULL) THEN
		SET NEW.VERSION =  IFNULL(OLD.VERSION,0) + 1;
    END IF;

    IF(( NEW.modified_date_time IS NULL) OR (TO_SECONDS(NEW.modified_date_time) IS NULL)
     OR (TO_SECONDS(NEW.modified_date_time) = 0  OR NEW.modified_date_time = OLD.modified_date_time)) THEN
		SET NEW.modified_date_time = CURRENT_TIMESTAMP;
    END IF;

    IF( (NEW.modified_by_user_id IS NULL) OR (NEW.modified_by_user_id = ' ')) THEN
		SET NEW.modified_by_user_id = CURRENT_USER;
    END IF;

END;

$$;
