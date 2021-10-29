CREATE TABLE public.people (
	id int NOT NULL GENERATED ALWAYS AS IDENTITY,
	"name" varchar(45) NULL,
	age varchar(10) NULL,
	employment_status varchar(45) NULL,
	tax_id varchar(45) NULL,
	us_citizen bool NULL,
	gender varchar(10) NULL,
	occupation varchar(45) NULL
);
