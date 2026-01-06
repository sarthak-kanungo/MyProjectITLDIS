-- Data for View Installation Page (Completed Installations)

-- 1. Ensure VehicleDetails exist with ins_status='Y'
-- Note: '34' is the dealer_code for 'Sonalika Dealers' in data.sql

INSERT INTO vehicledetails (vinid, vin_no, delivery_date, model_family, model_code, customer_name, mobile_ph, dealer_code, pdi_status, ins_status) VALUES
('INS001', 'INS-CHASSIS-001', '2023-11-01', 'DI-750 III', '503ID1669LDAL', 'Completed Customer 1', 9876543210, '34', 'Y', 'Y'),
('INS002', 'INS-CHASSIS-002', '2023-11-05', 'DI-745 III Rx', '453ID3588LDAJ', 'Completed Customer 2', 9876543211, 'DI-745 III Rx', 'Y', 'Y'),
('INS003', 'INS-CHASSIS-003', '2023-11-10', 'DI-60 MM Rx', '600ID2066LDAJ', 'Completed Customer 3', 9876543212, 'DI-60 MM Rx', 'Y', 'Y'),
('INS004', 'INS-CHASSIS-004', '2023-11-15', 'DI-35', '353IA3290LDAJ', 'Completed Customer 4', 9876543213, 'DI-35', 'Y', 'Y'),
('INS005', 'INS-CHASSIS-005', '2023-11-20', 'Worldtrac-90', '900ID3785LDBGUDE', 'Completed Customer 5', 9876543214, 'Worldtrac-90', 'Y', 'Y');

-- 2. Insert InstallationDetails
INSERT INTO installation_details (ins_no, vin_no, ins_date, dealer_code, photograph_file_name) VALUES
('INS/PB01/34/001', 'INS-CHASSIS-001', '2023-11-02', '34', 'photo1.jpg'),
('INS/PB03/DI745/002', 'INS-CHASSIS-002', '2023-11-06', 'DI-745 III Rx', 'photo2.jpg'),
('INS/PB04/DI60/003', 'INS-CHASSIS-003', '2023-11-11', 'DI-60 MM Rx', 'photo3.jpg'),
('INS/PB05/DI35/004', 'INS-CHASSIS-004', '2023-11-16', 'DI-35', 'photo4.jpg'),
('INS/PB06/WT90/005', 'INS-CHASSIS-005', '2023-11-21', 'Worldtrac-90', 'photo5.jpg');
