package com.ragibn5.devicedetect

enum class DeviceOS(val manufacturer: DeviceManufacturer) {
    ////
    // VERIFIED

    // Xiaomi
    XIAOMI_HYPEROS(DeviceManufacturer.XIAOMI),
    XIAOMI_MIUI(DeviceManufacturer.XIAOMI),

    // Transsion (Tecno, Infinix, Itel)
    TRANSSION_HIOS(DeviceManufacturer.TRANSSION),
    TRANSSION_XOS(DeviceManufacturer.TRANSSION),

    // BBK
    BBK_COLOR_OS(DeviceManufacturer.BBK),
    BBK_FUNTOUCH_OS(DeviceManufacturer.BBK),
    BBK_REALME_UI(DeviceManufacturer.BBK),
    BBK_ONEPLUS_OS(DeviceManufacturer.BBK),
    BBK_OXYGEN_OS(DeviceManufacturer.BBK),

    // Nothing
    NOTHING_OS(DeviceManufacturer.NOTHING),


    /*////
    // UNVERIFIED

    // Samsung
    SAMSUNG_ONE_UI(DeviceManufacturer.Samsung),
    SAMSUNG_TIZEN(DeviceManufacturer.Samsung),
    SAMSUNG_BADA(DeviceManufacturer.Samsung),

    // Huawei
    HUAWEI_EMUI(DeviceManufacturer.Huawei),
    HUAWEI_HARMONYOS(DeviceManufacturer.Huawei),

    // Vivo
    VIVO_FUNTOUCH(DeviceManufacturer.Vivo),
    VIVO_ORIGINOS(DeviceManufacturer.Vivo),

    // Google
    GOOGLE_STOCK(DeviceManufacturer.Google),
    GOOGLE_FUCHSIA(DeviceManufacturer.Google),

    // Sony
    SONY_XPERIA(DeviceManufacturer.Sony),

    // LG
    LG_UX(DeviceManufacturer.LG),
    LG_WEBOS(DeviceManufacturer.LG),

    // Motorola
    MOTOROLA_MYUX(DeviceManufacturer.Motorola),

    // Nokia
    NOKIA_STOCK(DeviceManufacturer.Nokia),

    // Asus
    ASUS_ZENUI(DeviceManufacturer.Asus),
    ASUS_ROGUI(DeviceManufacturer.Asus),

    // HTC
    HTC_SENSE(DeviceManufacturer.HTC),

    // Lenovo
    LENOVO_ZUI(DeviceManufacturer.Lenovo),

    // TCL
    TCL_UI(DeviceManufacturer.TCL),

    // ZTE
    ZTE_MIFAVOR(DeviceManufacturer.ZTE),

    // Fairphone
    FAIRPHONE_OS(DeviceManufacturer.Fairphone),

    // Essential
    ESSENTIAL_STOCK(DeviceManufacturer.Essential),

    // Amazon
    AMAZON_FIREOS(DeviceManufacturer.Amazon),

    // Microsoft
    MICROSOFT_WINDOWS_PHONE(DeviceManufacturer.Microsoft),
    MICROSOFT_WINDOWS_MOBILE(DeviceManufacturer.Microsoft),

    // BlackBerry
    BLACKBERRY_BB10(DeviceManufacturer.BlackBerry),
    BLACKBERRY_ANDROID(DeviceManufacturer.BlackBerry),*/

    // Generic/Unknown
    UNKNOWN(DeviceManufacturer.UNKNOWN);
}