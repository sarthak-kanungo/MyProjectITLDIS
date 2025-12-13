/* 
 * This js file for all alert message
 */

var pdiDate_currentDate_validation_msg = 'PDI Date should be within ';
var dat_validation_msg2=' days to Current Date.';
var tractorReceivedDate_currentDate_validation_msg = 'Tractor Received Date should be within ';
var tractorReceivedDate_pdiDate_validation_msg = 'Tractor Received Date cannot be greater than PDI Date.';
var invoice_currentDate_validation_msg = 'Invoice Date should be within ';
var invoice_pdiDate_validation_msg = 'Invoice Date cannot be greater than Tractor Received and PDI Date.';
var specialChar_validation_msg = 'Special Characters are not allowed in Field ::';
var standardCheckList_empty_validation_msg = 'Standard Checklist is not available for the given Model Code. Please Contact Administrator.';
var actiontaken_validation_msg='Please enter Action Taken against the NOT OK Check Point.';
var remark_validation_msg = 'Please enter Remarks against the NOT OK Check Point.';
var pdiConfirmation_msg ='You are going to submit the PDI, and it cannot be edited after Submit.\n Click OK to submit and Cancel to review and change the details.';
var submitconfirmation_validation_msg='You are going to submit the Installation, and it cannot be edited after\n Submit. Click OK to submit and Cancel to review and change the details.';
//common
var not_blank_validation_msg='Please Enter Value in Field :: ';
var not_blank_dropdown_validation_msg='Please Select Value in Field :: ';
var numeric_val_validation_msg='Please Enter Numeric value in Field :: '
var only_alphab_validation_msg='Please Enter Only Alphabets in Fields :: ' 
var maxLength_validation_msg='Cannot be greater than ';
var browser_validation_msg='Your Browser Does Not Support Ajax.'
var spc_validation_msg='Spacial characters semicolon and AND(\') are not permitted in '
// Warranty
var failed_part_validation_msg='Please attach Photograph of Failed Parts.';
var dispatch_date_validation_msg='Please enter Dispatched Date.';
var dispatch_date_check_validation_msg='Dispatched Date cannot be greater than current date.';
var courier_copy_validation_msg='Please attach the copy of GR/Courier/RR';
var qty_chech_validation_msg='Approved Qty cannot be greater than Claimed Qty.';
var reason_rejection_validation_msg='Please select the Reason for Rejection.';

//installation

var notblank_custname_validation_msg='Please enter Customer Name.'
var notblank_fathername_validation_msg='Please enter Father Name.'
var notblank_mobno_validation_msg='Please enter Contact No. '
var notblank_village_validation_msg='Please enter Village Name.'
var notblank_relwithcust_validation_msg='Please enter the Relationship with Customer.'
var invalid_custname_validation_msg='Special characters are not allowed in Customer Name.'
var invalid_fathername_validation_msg='Special characters are not allowed in Father Name.'
var invalid_mobno_validation_msg='Invalid Contact No. Please enter the Contact numbers with comma separation.'
var invalid_village_validation_msg='Special characters are not allowed in Village Name.'
var invalid_relwithcust_validation_msg='Special characters are not allowed in Relationship field.'
var repeat_relwithcust_validation_msg='Duplicate Customer Name not allowed.'
var notblank_standardcheck_validation_msg='Please select atleast one instruction.'
var notblank_majorapplication_validation_msg='Please select atleast one Major Application.'
var notblankservicebookletno_validation_msg='Special characters are not allowed in Service Booklet No.'
var notblankinstallername_validation_msg='Please select Installer Name.'
var notblankphotograph_validation_msg='Please upload the Photograph of Customer with Implement during Installation.'
var invalidothertractor_validation_msg='Special characters are not allowed in Other Tractor Details.'
var invalidpaymentmode_validation_msg='Special characters are not allowed in Payment Mode.'
var invalidbankname_validation_msg='Special characters are not allowed in Bank Name.'
var notfoundtractorinfo_validation_msg='Tractor Information not found.'
var visitdate_validation_msg='Visit Date cannot be greater than Current Date.'
var deliverydate_validation_msg='Delivery Date cannot be greater than Current Date.'
var deliveryvisitdate_validation_msg='Delivery Date cannot be greater than Visit Date.'
var invalidbrowsefile_validation_msg='Invalid File. Please Browse only valid files.'
var onlyjpgbrowsefile_validation_msg='Invalid File. Please Browse only .jpeg files.'
var constantfinaldate_validation_msg='Final Installation Date should be within'
var notblankfinalinsdate_validation_msg='Please enter Final Installation Date.'
var constantfinalvisitdate_validation_msg='Final Installation Date cannot be less than visit Date.'
var filesize_validation_msg='Upload image size should not be greater than 300 Kb.'

//Re-open job card
var reopenConfirm_validation_msg ='Do you want to Re-open Job Card No. '
var rejectConfirm_validation_msg ='Do you want to Reject Job Card No. '
var notblankdidate_validation_msg ='please enter Dealer Invoice Date . '
var vistdtvsdinvdt_validation_msg ='Dealer Invoice Date should be less than Visit Date '
var numvalue_validation_msg ='Enter numeric value in'
var date_validation_msg ='From Date should be less than To Date'
var mincontact_validation_msg ='Please enter minimum 6 digit in'

//jobcard vehicleinfo

var notblankchassisno_validation_msg='Please enter Chassis No.'
var numberhmr_validation_msg='Please enter numeric value in HMR.'
var notblankhmr_validation_msg='Please enter HMR.'
var saledatecurrent_validation_msg='Sale Date cannot be greater than Current Date.'
var tractorindate_validation_msg='Tractor In Date cannot be greater than Current Date.'
var tractorwith_validation_msg='Tractor In Date should be within '
var sysncdata_confirmation_msg='No data found for given Chassis No. \nClick OK if you want to download the details from ITL server. \nOtherwise click Cancel to continue manually.'
var sysncdata_validation_msg='The given Chassis No does not exists on ITL server. Kindly contact Administrator. \nNo data found for chassis no - '
var modalcode_validation_msg='Warranty does not exist against the given Model Code.\n So, Warranty Applicability is No. Do you want to save the Job Card?'
var hmrworking_validation_msg='Please select HMR Working.'
var autochasissno_validation_msg='Please enter atleast 3 characters in Chassis No.'
var invalidchassisno_validation_msg='Invalid Chassis No'
var invalidcoupon_validation_msg='Invalid Coupon No'
var invaliddealerjobcardno_validation_msg='Invalid Dealer Job Card No'

var jobcardwty_validation_msg ='Warranty Applicability is YES. Do you want to save the Job Card?'
var jobcardnwty_validation_msg ='Warranty Applicability is NO. Do you want to save the Job Card?'
var saledate_validation_msg='Please enter Sale Date'

var wtyapp_validation_msg ='Warranty should be Yes or No'
var eventdate_validation_msg ='Tractor In date should be within Event Period '


//customerinfo



var payeemobileph_validation_msg='Please enter only numeric value in Mobile Phone.'
var payeelandline_validation_msg='Please enter only numeric value in Landline.'
var payeepincode_validation_msg='Please enter only numeric value in PinCode.'
var payeeemail_validation_msg='Please enter valid E-mail ID.'
var invalid_validation_msg='Invalid'


var custpincode_validation_msg='Please enter valid Customer Pin Code.'
var custname_validation_msg='Please enter valid Customer Name.'
var vill_validation_msg='Please enter valid Customer Village.'
var mobileph_validation_msg='Please enter only numeric value in Customer Mobile Phone.'
var landline_validation_msg='Please enter only numeric value in Customer Landline.'
var sizeoflandhold_validation_msg='Please enter valid Size of Land Holding.'
var maincrop_validation_msg='Please enter valid Main Crops.'
var occupation_validation_msg='Please enter valid Customer Occupation.'
var district_validation_msg='Please enter valid Customer District.'
var pincode_validation_msg='Please enter valid Customer Pin Code.'
var email_validation_msg='Please enter valid E-mail ID.'
var taluka_validation_msg='Please enter valid Taluka/Tehsil.'
var state_validation_msg='Please enter valid State.'
var country_validation_msg='Please enter valid Country.'
var custinvalid_validation_msg='Invalid'
var isSarathiPart_validation_msg='Sarathi Oil part no is not allowed for Dealers in PO Order'


// Status
var vehInfo_validation_msg='Please fill the Vehicle Information Details.'
var workstart_validation_msg='Work Started On should be less than Current Date.'
var workfinish_validation_msg='Work Finished On should be less than Current Date.'
var vehicaleDt_validation_msg='Vehicle Out On should be less than Current Date.'
var workstartGT_validation_msg='Work Started On should be greater than Tractor In Date.'
var workfinishGT_validation_msg='Work Finished On should be greater than Work Started On.'
var vehicalDTGT_validation_msg='Vehicle Out On should be greater than Work Finished On.'
var payee_validation_msg='Please fill the Contact Name under Customer Infomation.'
var closeJC_validation_msg='Do you want to Close JobCard No. '
var min_validation_msg=' Minute should not be same as '
var minGT_validation_msg=' Minute should be greater than '
var hrGT_validation_msg=' Hours should be greater than '
var warrantyapp_validation_msg='Parts cannot be selected in Warranty as Warranty Applicability is NO, Please go to Actuals to change Bill Type of warranty parts'
var checkactiontaken_validation_msg='Please go to Actuals to enter the Action Taken against each complaint'
var checkComplaintStatus_validation_msg='Please enter Complaint details in Job Card'

//Actual
var partunique_validation_msg='Same part number cannot be entered twice.'
var partDescunique_validation_msg='Same part description cannot be entered twice.'
var compunique_validation_msg ='Same complaint cannot be entered twice.'
var discount_validation_msg='Discount % cannot be greater than 100.'
var workunique_validation_msg='Same work cannot be selected twice.'
var totdisc_validation_msg='Total Discount cannot be greater than Total Actuals.'
var lubeunique_validation_msg='Same part number cannot be entered twice in lubes.'
var lubeDescunique_validation_msg='Same part description cannot be entered twice in lubes.'
var partno_msg = 'Part Number '
var exist_msg =' does not exist in database.'
var partNosugg_validation_msg='Please enter atleast four character of part number to get suggestions.'
var partNomadat_validation_msg='Please enter atleast one part number.'
var chassisno_validation_msg='Please enter Chassis No.'

var workAmt_validation_msg='Work amount should be numeric only.'
var compCausal_validation_msg='Only one Causal part can be selected against each Complaint.'
var availableqty_validation_msg='Current Available Quantity in Inventory is '
var qty_validation_msg=' .So, quantity cannot be entered more than the Available Quantity'
var prdate_validation_msg='Promised Date should be greater than the Tractor in Date'
var rcdate_validation_msg='Required by Customer Date should be greater than Tractor in Date'
var actdate_validation_msg='Actual Date should be greater than the Tractor in Date'
var wrrtyapp_validation_msg='As Warranty is not Applicable, so \"WTY\" Bill Type cannot be selected.'
var partdescnotfound_validation_msg='Part Description not found'
var partnotfound_validation_msg='Part No. not found'
var actionamount_validation_msg='Amount should be greater than 0'
var zeroqty_validation_msg='Part Nos. have Zero Quantity will not be save';

//Complaint
var obs_validation_msg='Foreman Observation cannot be greater than '
var custver_validation_msg='Customer Verbatim cannot be greater than '
var uniquecustVerbatim_validation_msg='Same Customer Verbatim cannot be entered twice.'
var custVerbatim_validation_msg='Please enter only alphabets in Customer Verbatim.'
var obsForeman_validation_msg='Please enter only alphabets in Foreman Observation.'
var onecompmand_validation_msg='Atleast One complaint is mandatory.'
var deletepreviouscomp_validation_msg='Cannot delete previous complaints.'


//estimate

var partnofound_validation_msg='Unit Price does not exists against the given part. So, it cannot be added in Job Card.'
var descnotfound_validation_msg='Unit Price does not exists against the given Description. So, it cannot be added in Job Card.'
var nosuggavail_validation_msg='No Suggestions available.'

//view Inventory
var binlocsuccess_validation_msg='Bin Location successfully updated for Part No. :'
var binlocfailure_validation_msg='Bin Location not updated, Please Contact System Administrator.'
var filter_validation_msg='Please select atleast one filter value.'

//counter sale
var numeric_msg='must be Numeric.'
var contactNumeric_msg='Please Enter Numeric Value in Contact No.'
var contactLength_msg='Contact No must be 10 Digits.'
var qtyCheck_msg='can not be greater than Available Qty which is :'
var gt_than_msg='can not greater than'
var proper_validation_msg='Invalid Number.(Only two numbers are allowed after decimal.)'
var returnDateVali_msg='Return Date can not less than Invoice Date.'
var retrunDateRange_valid_msg='Return Date must be with in 30 days from Invoice Date.'

var atleastOneCheck_validation_msg='Please Select atleast one '
var noNeed_value_validation_msg='No need to Enter Value in Field '
var greater_than_val_msg='must be greater than'
var range_of_val_msg='must be in the range of'
var valid_vendorCode_val_msg='Please Enter Valid Vendor Code .'
var receivedOnDate_val_msg='Received On Date should be greater than Invoice Date.'
var part_number_not_serviceable_msg='Part numbers highlighted in red are not serviceabel.'
var invoice_bill_currentDate_validation_msg = 'Bill Date should be within ';
var jobCard_not_open_message = 'Job card is not in open stage.';
var manualEntryForPDIValidationMessage="PDI Job card is not allowed against manual entry of chassis.\nPlease select job type other than PDI."
var validation_msg='Special characters are not allowed in'
var paintedPart_msg='Please Order on Painted Part Numbers: '
var notServicable_OR_pricenotAvailable_msg='Part is Not Serviceable or Price is not available'
var alternatePart_msg='Please order the Alternate Part No. for'
var priceNotAvailable_msg='Price is not available'
var notServicable_msg='Part is Not Serviceable.'
var mOQ_msg='Qty must be multiple of MOQ : '
var order_confirm_msg='Do you want to cancel order & return back to previous screen ?'
var please_Enter_msg='Please enter value in '
var no_alternate_part_available='No alternate part available for Part No. '
var qty_msg='PI quantity can not be 0.'
var qty_msg1='PI Quantity can not be more than Ordered Quantity '
var only_numbers='Only numbers are allowed in '
var not_change_status_msg='Status must be Alternate Available'
var complaintDategreater_validation_msg='Complaint Date can not be less than Tractor In Date.'
var complaintDate_validation_msg='Complaint Date cannot be greater than current date.'


var prdatedatecurrent_validation_msg='Promised Date cannot be greater than Current Date.'
var rcdatedatecurrent_validation_msg='Required by Customer Date cannot be greater than Current Date.'
var actdatedatecurrent_validation_msg='Actual Date cannot be greater than Current Date.'
var dateofComplaintwith_validation_msg='Date of Complaint should be within '
var jobcardBEW_validation_msg='Job Card with job type "BEW", that only those parts can be selected under BEW which are applicable'
var partNotAllowedForAddInv_validation_msg='This Part No '
var partNotAllowedForAddInv_validation_msg1='is not allowed for add inventory from other dealer.'
