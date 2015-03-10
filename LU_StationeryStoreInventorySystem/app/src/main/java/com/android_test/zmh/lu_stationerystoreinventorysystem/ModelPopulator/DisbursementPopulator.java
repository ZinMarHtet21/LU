package com.android_test.zmh.lu_stationerystoreinventorysystem.ModelPopulator;

import com.android_test.zmh.lu_stationerystoreinventorysystem.IPopulator.IDisbursement;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Disbursement;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by student on 6/3/15.
 */
public class DisbursementPopulator implements IDisbursement {
    @Override
    public List<Disbursement> populateDisbursement() {

        List<Disbursement> disb_list = new LinkedList<Disbursement>();

        Disbursement disbursement = new Disbursement("Dis01",(new Date(System.currentTimeMillis())),"Clip","ENGL",10,30);
        Disbursement disbursement2 = new Disbursement("Dis02",(new Date(System.currentTimeMillis())),"Envelope","COMM",5,20);
        Disbursement disbursement3 = new Disbursement("Dis03",(new Date(System.currentTimeMillis())),"File Transparent","ZOO",7,56);

        disb_list.add(disbursement);
        disb_list.add(disbursement2);
        disb_list.add(disbursement3);

        return disb_list;
    }
}
