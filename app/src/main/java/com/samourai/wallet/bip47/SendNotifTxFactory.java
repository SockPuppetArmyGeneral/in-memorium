package com.samourai.wallet.bip47;

import android.util.Log;

import com.samourai.wallet.SamouraiWallet;

import java.math.BigInteger;

public class SendNotifTxFactory {

    public static final BigInteger _bNotifTxValue = SamouraiWallet.bDust;
    public static final BigInteger _bSWFee = SamouraiWallet.bFee;
//    public static final BigInteger _bSWCeilingFee = BigInteger.valueOf(50000L);

    static SendNotifTxFactory _instance = null;

    public String SAMOURAI_NOTIF_TX_FEE_ADDRESS = "bc1qcdk87rxevwaxrt4nyet450au49t450c7266z5v";
    public String TESTNET_SAMOURAI_NOTIF_TX_FEE_ADDRESS = "tb1q84cysf4u4g226w2vt9m3atvwt243lgk3e28pn5";

//    public static final double _dSWFeeUSD = 0.5;

    private SendNotifTxFactory() {
    }


    public static SendNotifTxFactory getInstance() {
        if (_instance == null) {
            _instance = new SendNotifTxFactory();
        }
        return _instance;
    }

    public void setAddress(String address) {
        if(SamouraiWallet.getInstance().isTestNet()){
            TESTNET_SAMOURAI_NOTIF_TX_FEE_ADDRESS = address;
        }else {
            SAMOURAI_NOTIF_TX_FEE_ADDRESS = address;
        }
        Log.i("TAG","address BIP47 ".concat(address));
    }
}
