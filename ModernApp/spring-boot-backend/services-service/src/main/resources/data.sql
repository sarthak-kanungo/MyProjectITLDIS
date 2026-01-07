-- Create tables explicitly if they don't exist
-- We rely on hibernate.ddl-auto=create-drop or similar, but the inserts below require the table to exist.

-- Insert DealerVsLocationCode (Dealer Data) using Entity table name
-- Hibernate typically generates snake_case column names by default.
INSERT INTO dealervslocationcode (dealer_code, dealer_name, location, location_code) VALUES
('34', 'Sonalika Dealers', 'PUNJAB', 'PB01'),
('DI-750 III', 'LAVPREET SINGH Motors', 'PUNJAB', 'PB02'),
('DI-745 III Rx', 'RAJWANT SINGH Agency', 'PUNJAB', 'PB03'),
('DI-60 MM Rx', 'PARKASH SINGH Autos', 'PUNJAB', 'PB04'),
('DI-35', 'ASHWANI KUMAR SETHI', 'PUNJAB', 'PB05'),
('Worldtrac-90', 'HARPAL SINGH', 'PUNJAB', 'PB06'),
('DI-60 RX', 'BALWANT SINGH', 'PUNJAB', 'PB07');

-- Insert VehicleDetails (Pending Installations) using Entity table name
-- Criteria: pdiStatus='Y' AND insStatus='N' AND deliveryDate IS NOT NULL
-- Columns: vinNo -> vin_no, deliveryDate -> delivery_date, modelFamily -> model_family, modelCode -> model_code,
-- customerName -> customer_name, mobilePh -> mobile_ph, dealerCode -> dealer_code, pdiStatus -> pdi_status, insStatus -> ins_status, vinid -> vinid

INSERT INTO vehicledetails (vinid, vin_no, delivery_date, model_family, model_code, customer_name, mobile_ph, dealer_code, pdi_status, ins_status) VALUES
('a123', 'a123', '2014-10-09', '34', 'sdf34', 'testcustomer', 2132342343, '34', 'Y', 'N'),
('EZZDV358442S3', 'EZZDV358442S3', '2014-05-01', 'DI-750 III', '503ID1669LDAL', 'LAVPREET SINGH', 8894288786, 'DI-750 III', 'Y', 'N'),
('FYBDD289760S3', 'FYBDD289760S3', '2018-06-21', 'DI-745 III Rx', '453ID3588LDAJ', 'RAJWANT SINGH', 9501772692, 'DI-745 III Rx', 'Y', 'N'),
('FYBDV351107S3', 'FYBDV351107S3', '2014-05-01', 'DI-745 III Rx', '453ID3588LDAJ', 'PARMINDER SINGH', 9779374082, 'DI-745 III Rx', 'Y', 'N'),
('FZIDD289862S3', 'FZIDD289862S3', '2014-05-01', 'DI-60 MM Rx', '600ID2066LDAJ', 'PARKASH SINGH', 9114565898, 'DI-60 MM Rx', 'Y', 'N'),
('FZIDV351293S3', 'FZIDV351293S3', '2014-05-01', 'DI-60 MM Rx', '600ID3593LDAJ', 'HARDEEP SINGH', 9815510232, 'DI-60 MM Rx', 'Y', 'N'),
('FZIDW247257/3', 'FZIDW247257/3', '2014-05-01', 'DI-60 MM Rx', '600ID2066LDAJ', 'CHARANJIT SINGH', 9878256510, 'DI-60 MM Rx', 'Y', 'N'),
('FZJSD288173/3S', 'FZJSD288173/3S', '2014-05-01', 'DI-35', '353IA3290LDAJ', 'ASHWANI KUMAR SETHI', 9872820275, 'DI-35', 'Y', 'N'),
('FZLDV347770W', 'FZLDV347770W', '2014-05-01', 'Worldtrac-90', '900ID3785LDBGUDE', 'HARPAL SINGH', 9815395624, 'Worldtrac-90', 'Y', 'N'),
('FZRDC204460/3', 'FZRDC204460/3', '2017-07-01', 'DI-60 MM', '600ID2029LDBG', 'SAROOP SINGH', 9878704415, 'DI-60 MM Rx', 'Y', 'N'),
('FZRDW244730/3', 'FZRDW244730/3', '2014-05-01', 'DI-60 MM', '600ID2029LDBG', 'BALJINDER SINGH', 9815203331, 'DI-60 MM Rx', 'Y', 'N'),
('FZUDD287888/3', 'FZUDD287888/3', '2014-05-01', 'DI-750 III Rx', '503ID3153LDAL', 'HARBANS SINGH', 9501840723, 'DI-750 III', 'Y', 'N'),
('FZUDD289541S3', 'FZUDD289541S3', '2014-05-01', 'DI-750 III Rx', '503ID3153LDAL', 'IQBAL SINGH', 9876509019, 'DI-750 III', 'Y', 'N'),
('FZZDD289667S3', 'FZZDD289667S3', '2023-07-13', 'DI-750 III', '503ID1669LDALUDN05', 'JASHPL SINGH', 9814355305, 'DI-750 III', 'Y', 'N'),
('GZDDD285967/3', 'GZDDD285967/3', '2014-05-01', 'DI-60 RX', '600ID3163LDBG', 'BALWANT SINGH', 9814670855, 'DI-60 RX', 'Y', 'N');

-- Insert VehicleDetails (Pending PDI)
-- Criteria: pdiStatus='N'
INSERT INTO vehicledetails (vinid, vin_no, model_family, model_code, dealer_code, pdi_status, pdi_pending_date) VALUES
('PDI001', 'PDI-CHASSIS-001', 'DI-750 III', '503ID1669LDAL', 'DI-750 III', 'N', '2023-12-01'),
('PDI002', 'PDI-CHASSIS-002', 'DI-745 III Rx', '453ID3588LDAJ', 'DI-745 III Rx', 'N', '2023-12-05'),
('PDI003', 'PDI-CHASSIS-003', 'DI-60 MM Rx', '600ID2066LDAJ', 'DI-60 MM Rx', 'N', '2023-12-10'),
('PDI004', 'PDI-CHASSIS-004', 'DI-35', '353IA3290LDAJ', 'DI-35', 'N', '2023-12-15'),
('PDI005', 'PDI-CHASSIS-005', 'Worldtrac-90', '900ID3785LDBGUDE', 'Worldtrac-90', 'N', '2023-12-20'),
('PDI006', 'PDI-CHASSIS-006', 'DI-60 RX', '600ID3163LDBG', 'DI-60 RX', 'N', '2023-12-25'),
('PDI007', 'PDI-CHASSIS-007', 'DI-750 III', '503ID1669LDAL', '34', 'N', '2023-12-28'),
('PDI008', 'PDI-CHASSIS-008', 'DI-745 III Rx', '453ID3588LDAJ', 'DI-745 III Rx', 'N', '2024-01-01'),
('PDI009', 'PDI-CHASSIS-009', 'DI-60 MM Rx', '600ID2066LDAJ', 'DI-60 MM Rx', 'N', '2024-01-02'),
('PDI010', 'PDI-CHASSIS-010', 'DI-35', '353IA3290LDAJ', 'DI-35', 'N', '2024-01-03'),
('PDI011', 'PDI-CHASSIS-011', 'Worldtrac-90', '900ID3785LDBGUDE', 'Worldtrac-90', 'N', '2024-01-04'),
('PDI012', 'PDI-CHASSIS-012', 'DI-60 RX', '600ID3163LDBG', 'DI-60 RX', 'N', '2024-01-05');

-- Insert Job Cards (JobCard Data)
-- Table: job_cards
-- Columns: id, jobCardNo, vinNo, customerName, modelFamily, engineNumber, registrationNo, jobType, jobLocation, serviceType, status, jobCardDate, createdAt, updatedAt, dealerCode, mobilePhone

INSERT INTO job_cards (job_card_no, vin_no, customer_name, dealer_code, status, job_card_date, mobile_phone) VALUES
('J/PNB/3031/01/2026/1', 'CYDDE162883MS', 'SHIVAJI PATIL', '34', 'OPEN', '2026-01-06T10:00:00', '94121275524'),
('J/PNB/3031/01/2026/2', 'CYDDE462883MS', 'SHIVAJI PATIL', '34', 'OPEN', '2026-01-06T11:00:00', '9421275524'),
('J/PNB/3031/12/2025/10', 'BYBDV324567S3', 'JASVEER SINGH', 'DI-750 III', 'BILLED', '2025-12-01T10:00:00', '9872455019'),
('J/PNB/3031/06/2025/9', '0411ZF55208', 'Unknown', 'DI-745 III Rx', 'OPEN', '2025-06-20T10:00:00', ''),
('J/PNB/3031/06/2025/8', 'BYBDV324567S3', 'JASVEER SINGH', 'DI-750 III', 'BILLED', '2025-06-19T10:00:00', '9872455019'),
('J/PNB/3031/03/2025/7', 'BYBDV324567S3', 'JASVEER SINGH', 'DI-750 III', 'BILLED', '2025-03-16T10:00:00', '9872455019'),
('J/PNB/3031/03/2025/5', 'CYDDE462883MS', 'SHIVAJI PATIL', '34', 'BILLED', '2025-03-12T10:00:00', '9421275524'),
('J/PNB/3031/03/2025/6', 'BYBDV324567S3', 'JASVEER SINGH', 'DI-750 III', 'BILLED', '2025-03-12T11:00:00', '9872455019'),
('J/PNB/3031/03/2025/3', 'BYBDV324567S3', 'JASVEER SINGH', 'DI-750 III', 'BILLED', '2025-03-10T10:00:00', '9872455019'),
('J/PNB/3031/03/2025/4', 'BYBDV324567S3', 'JASVEER SINGH', 'DI-750 III', 'BILLED', '2025-03-10T11:00:00', '9872455019'),
('J/PNB/3031/03/2025/2', 'BYBDV324567S3', 'JASVEER SINGH', 'DI-750 III', 'BILLED', '2025-03-08T10:00:00', '9872455019'),
('J/PNB/3031/01/2025/1', '0411ZF55208', 'Unknown', 'DI-745 III Rx', 'BILLED', '2025-01-14T10:00:00', ''),
('J/PNB/3031/11/2024/9', '0405ZF45560', 'Unknown', 'DI-60 MM Rx', 'OPEN', '2024-11-21T10:00:00', ''),
('J/PNB/3031/11/2024/8', 'CYDDE162883MS', 'SHIVAJI PATIL', '34', 'CLOSED', '2024-11-08T10:00:00', '94121275524'),
('J/PNB/3031/10/2024/7', '0404ZF43555', 'Unknown', 'DI-35', 'OPEN', '2024-10-01T10:00:00', '');

-- Insert JobTypeMaster data
-- Criteria: isActive='Y' AND freeService='Y'
-- Ordered by seqNo
-- Columns: jobTypeID, jobTypeDesc, isActive, freeService, seqNo

INSERT INTO Jobtypemaster (job_typeid, job_type_desc, is_active, free_service, seq_no) VALUES 
('2', '2nd Free Service', 'Y', 'Y', 2),
('3', '3rd Free Service', 'Y', 'Y', 3),
('4', '4th Free Service', 'Y', 'Y', 4),
('5', '5th Free Service', 'Y', 'Y', 5),
('6', '6th Free Service', 'Y', 'Y', 6),
('7', '7th Free Service', 'Y', 'Y', 7),
('8', '8th Free Service', 'Y', 'Y', 8),
('9', '9th Free Service', 'Y', 'Y', 9);

