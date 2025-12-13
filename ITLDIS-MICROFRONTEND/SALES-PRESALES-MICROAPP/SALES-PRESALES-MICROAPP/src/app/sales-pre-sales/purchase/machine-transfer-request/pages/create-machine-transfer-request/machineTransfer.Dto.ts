

export interface SaveMAchineTransfer {
    coDealer: CoDealer;
    dealerMachineTransferDetails: DealerMachineTransferDetail[];
    totalQty: number;
}

export interface DealerMachineTransferDetail {
    machineMaster: CoDealer;
    quantity: number;
}

export interface CoDealer {
    id: number;
}