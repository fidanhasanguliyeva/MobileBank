/*
 * Copyright
 * Namig Gadirov & IBAR
 * This code is copyrighted and using this code without agreement from authors is forbidden
 *
 */

package com.mobilebank.utils

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import java.util.Calendar
import java.util.TimeZone

/**
 * This object class is Analytics helper class. Hanalyticas helper class enables to connect to analytics server and send
 * data to there. Currently we are sending screen names and parameters as a measure data on analytics side
 */
object AnalyticsHelper {
    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    /**
     * Init analytics  by context
     */
    fun initAnalytics(context: Context) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context)
    }

    /**
     * Log the main events to measure. Such as payment success or pay result, Login actions, transfer actions and o.
     * @param eventName Which event category name do you want to send ( Ex. Payment)
     * @param params Which parameters do you want to send ( Ex. is_success)
     * @see Events
     * @see Params
     */
    fun logEvent(eventName: String, name: String) {
        val bundle = Bundle()

        val timeZone = TimeZone.getTimeZone("Asia/Baku")
        val currentTime = Calendar.getInstance(timeZone)
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)
        val second = currentTime.get(Calendar.SECOND)
        val millisecond = currentTime.get(Calendar.MILLISECOND)

        bundle.putString("time", "$hour:$minute:$second.$millisecond")
        bundle.putString("name", name)
        mFirebaseAnalytics?.logEvent(eventName, bundle)
    }

    /**
     * Log screen name. This screen name will be main container fragment
     * @param activity from which activity screen name will start
     * @param screenName which screen name you will set. The main screen name look [ScreenNames]
     * @see ScreenNames
     */
    fun addScreenTrack(activity: Activity, screenName: String) {
        mFirebaseAnalytics?.setCurrentScreen(activity, screenName, null)
    }

    /**
     * Sets user id to firebase to track it at the future
     * @param userId user credential which can be came from kobil or back end service
     */
    fun setUserId(userId: String) {
        try {
            mFirebaseAnalytics?.setUserId(userId)
        } catch (e: Exception) {
            //log it at the future
        }
    }

    /**
     * removes  user id to firebase
     */
    fun removeUserId() {
        try {
            mFirebaseAnalytics?.setUserId(null)
        } catch (e: Exception) {
            //log it at the future
        }
    }

    /**
     * This object class is responsible to analyse firebase analytics screens
     * If there is new screens you should add this screen name into here for future analysis
     */
    object ScreenNames {
        const val MAIN_PAGE = "mainpage"
        const val PAYMENT_MERCHANTS = "payment_merchants"
        const val PAYMENT_CHECK = "payment_check"
        const val PAYMENT_SELECT = "payment_select"
        const val PAYMENT_PAY = "payment_pay"
        const val PAYMENT_SELECT_PAYTYPE = "payment_select_pay_type"
        const val PAYMENT_CONFIRM = "payment_confirm"
        const val PAYMENT_AUTOPAY = "payment_autopay"
        const val PAYMENT_SUCCESS = "payment_success"
        const val PAYMENT_FAIL = "payment_fail"
        const val PAYMENT_DETAIL = "payment_detail"
        const val TRANSFER_LIST = "transfer_list"
        const val TRANSFER_BETWEEN_OWN = "transfer_between_own"
        const val TRANSFER_TO_OTHER_CARD = "transfer_to_other_card"
        const val TRANSFER_TO_IBAR_ACCOUNT = "transfer_to_ibar_account"
        const val TRANSFER_TO_DOMESTIC = "transfer_to_domestic"
        const val TRANSFER_SUCCESS = "transfer_success"
        const val TRANSFER_FAIL = "transfer_fail"
        const val TRANSFER_DETAIL = "transfer_detail"
        const val OTHERS_SCREEN = "others_screen"
        const val CURRENCY = "currency"
        const val LANGUAGE = "language"
        const val NEWS_AND_CAMPAIGN = "news_and_campaign"
        const val DEVICES = "devices"
        const val CONTACTS = "contacts"
        const val TERMS_AND_CONDITION = "terms_and_condition"
        const val PRODUCT_DETAIL_MENU = "product_detail_menu"
        const val ACCOUNT_DETAIL = "account_detail"
        const val LOAN_DETAIL = "loan_detail"
        const val DEPOSIT_DETAIL = "deposit_detail"
        const val CARD_INFO = "card_info"
        const val ACCOUNT_INFO = "account_info"
        const val LOAN_INFO = "loan_info"
        const val CARD_TRANSACTION_HISTORY = "card_transaction_history"
        const val ACCOUNT_TRANSACTION_HISTORY = "account_transaction_history"
        const val LOAN_TRANSACTION_HISTORY = "loan_transaction_history"
        const val LOAN_TRANSACTION_DETAIL = "loan_transaction_detail"
        const val TRANSACTION_HISTORY_DETAIL = "transaction_history_detail"
        const val NOTIFICATIONS = "notifications"
        const val QAYGICASH_CATEGORY = "qaygicash_category"
        const val TAM_CARD_CATEGORY = "tam_card_category"
        const val QAYGICASH_PARTNER = "qaygicash_partner"
        const val TAM_CARD_PARTNER = "tam_card_partner"
        const val TAM_CARD_MAP = "tam_card_map"
        const val AZERCELL_BONUS = "azercell_bonus"
        const val AZERCELL_INFO = "azercell_info"

        // edv screens
        const val EDV_QR_SCAN = "edv_qr"
        const val EDV_MANUAL = "edv_manual"
        const val EDV_RECEIPT_ADD = "edv_add_confirmation"
        const val EDV_RECEIPTS = "edv_receipts"
        const val EDV_TERMS = "edv_terms"
        const val EDV_TERMS_DETAIL = "edv_terms_detail"
        const val EDV_RECEIPT_DETAILS = "edv_receipt_detail"
    }

    /**
     * This object class is responsible to analyse firebase analytics events
     * if there are any action type to log into firebase it can be added into here and use at future
     * to log events
     */
    object Events {

        const val PAYMENT_PAY = "payment_pay"
        const val TRANSFER_SEND = "transfer_send"


        //Card Order
        const val ONLINE_ORDER_CARD_DASHBOARD = "online_order_card_dashboard"
        const val ONLINE_ORDER_CARD_BANK_PRODUCTS = "online_order_card_bankproducts"
        const val ONLINE_ORDER_CARD_GET_CARD = "online_order_card_get_card"
        const val ONLINE_ORDER_CARD_VERIFY_PIN = "online_order_card_verify_pin"
        const val ONLINE_ORDER_CARD_AZERCELL = "online_order_card_azercell"
        const val ONLINE_ORDER_CARD_CARD_PREFERENCES = "online_order_card_card_preferences"
        const val ONLINE_ORDER_CARD_COMMISSION_PAYMENT = "online_order_card_commission_payment"
        const val ONLINE_ORDER_CARD_FATCA = "online_order_card_fatca"


        //EDV
        const val EDV_CARD_ACTIVATE_SUCCESS = "edv_card_activate_success"
        const val EDV_CARD_ACTIVATE_FAILURE = "edv_card_activate_failure"
        const val EDV_QR_READ_SUCCESS = "edv_qr_read_success"
        const val EDV_QR_READ_FAILURE = "edv_qr_read_failure"
        const val EDV_MANUAL_TAPPED = "edv_manual_tapped"
        const val EDV_CHECK_SUCCESS = "edv_check_success"
        const val EDV_CHECK_FAILURE = "edv_check_failure"
        const val EDV_ADD_SUCCESS = "edv_add_success"
        const val EDV_ADD_FAILURE = "edv_add_failure"
        const val SCAN_EDV_DASHBOARD = "scan_edv_dashboard"

        //PARKING
        const val PARKING_READ_SUCCESS = "parking_read_success"
        const val PARKING_CHECK_SUCCESS = "parking_check_success"
        const val PARKING_CHECK_FAILURE = "parking_check_failure"

        //Payment Funnel
        const val PAYMENT_MERCHANTS = "payment_merchants"
        const val PAYMENT_MERCHANTS_DATA = "payment_merchants_data"
        const val PAY_BUTTON_CLICK = "pay_button_click"
        const val PAYMENT_CONFIRM_CANCEL = "payment_confirm_cancel"
        const val PAYMENT_CONFIRM_APPROVE = "payment_confirm_approve"
        const val PAYMENT_CREATE_AUTOPAY = "payment_autopay_create"
        const val PAYMENT_DELETE_AUTOPAY = "payment_autopay_delete"
        const val PAYMENT_DELETE_AUTOPAY_SUCCES = "payment_autopay_delete_success"
        const val PAYMENT_DELETE_AUTOPAY_FAIL = "payment_autopay_delete_fail"
        const val PAYMENT_EDIT_AUTOPAY = "payment_autopay_edit"
        const val PAYMENT_EDIT_AUTOPAY_SUCCES = "payment_autopay_edit_success"
        const val PAYMENT_EDIT_AUTOPAY_FAIL = "payment_autopay_edit_fail"
        const val PAYMENT_AUTOPAY_SETPAYMENT = "payment_autopay_setpayment"
        const val PAYMENT_AUTOPAY_SUCCES = "payment_autopay_create_success"
        const val PAYMENT_AUTOPAY_FAIL = "payment_autopay_create_fail"

        //Templates Funnel
        const val CREATE_TEMPLATE_FROM_TEMPLATELIST = "create_template_from_templatelist_click"
        const val TEMPLATE_MERCHANT_SELECTED = "template_merchant_selected"
        const val TEMPLATE_MERCHANT_CHECK = "template_merchant_check"
        const val TEMPLATE_SAVE_BUTTON_CLICK = "template_save_button_click"
        const val TEMPLATE_SAVE_SUCCESS_EVENT = "template_save_success_event"
        const val TEMPLATE_SAVE_ERROR_EVENT = "template_save_error_event"

        //Transfer
        const val TRANSFER_BETWEEN_OWN = "transfer_between_own"

        const val TRANSFER_OTHER_CARDS = "transfer_other_cards"
        const val TRANSFER_BUTTON_CLICK_OTHER_CARD = "transfer_button_click_other_card"


        const val TRANSFER_IBAR_ACCOUNTS = "transfer_ibar_accounts"
        const val TRANSFER_RESULT_IBAR_ACCOUNT = "transfer_result_ibar_account"

        //Loan funnel
        const val LOAN_APPLICATION = "loan_application"
        const val LOAN_FINISH_APPLICATION_REQUEST = "loan_finish_application_request"
        const val LOAN_FATCA = "loan_fatca"

        // Account funnel
        const val ACCOUNT_FATCA = "account_fatca"
        const val ACCOUNT_OPEN_FINISH_APPLICATION_REQUEST =
            "account_open_finish_application_request"
        const val ACCOUNT_OPEN_APPLICATION = "account_open_application"
        const val ACCOUNT_OPEN_VERIFY_PIN = "account_open_verify_pin"
        const val ACCOUNT_OPEN_VERIFY_PIN_ERROR = "account_open_verify_pin_error"

        //Tam Card Application
         const val TAM_CARD_FATCA = "tamkart_fatca"

        //Life Banking
        const val SPECIFIC_COUPON_BOUGHT = "spesific_coupon_bought"

    }

    /**
     * This object class is responsible to analyse firebase analytics even parameter
     */
    object Params {
        const val VENDOR = "vendor"
        const val IS_SUCCESS = "is_success"
        const val TYPE = "type"
        const val FAIL_RESULT = "fail_result"
        const val FAIL_CODE = "fail_code"
        const val CURRENCY = "currency"
        const val AMOUNT = "amount"
        const val COUPON_ID = "coupon_id"
    }

}