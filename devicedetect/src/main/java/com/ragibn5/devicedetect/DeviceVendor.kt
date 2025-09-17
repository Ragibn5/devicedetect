package com.ragibn5.devicedetect

import android.os.Build
import com.ragibn5.devicedetect.utils.DefaultPropertyReader
import com.ragibn5.devicedetect.utils.DefaultTerminal
import com.ragibn5.devicedetect.utils.PropertyReader

data class DeviceVendor(
    val manufacturer: DeviceManufacturer,
    val brand: DeviceBrand,
    val os: DeviceOS
) {
    companion object {
        private val propertyReader: PropertyReader = DefaultPropertyReader(DefaultTerminal())

        fun detect(): DeviceVendor {
            val (detectedManufacturer, detectedBrand) = detectManufacturerAndBrand()
            val os = detectOS()

            return DeviceVendor(
                manufacturer = detectedManufacturer,
                brand = detectedBrand,
                os = os
            )
        }

        private fun detectManufacturerAndBrand(): Pair<DeviceManufacturer, DeviceBrand> {
            val brand = Build.BRAND.lowercase()
            val model = Build.MODEL.lowercase()
            val manufacturer = Build.MANUFACTURER.lowercase()

            return when {
                // Xiaomi - Tested
                "xiaomi" in manufacturer && ("redmi" in brand || "redmi" in model) ->
                    DeviceManufacturer.XIAOMI to DeviceBrand.REDMI

                "xiaomi" in manufacturer && ("poco" in brand || "poco" in model) ->
                    DeviceManufacturer.XIAOMI to DeviceBrand.POCO

                "xiaomi" in manufacturer ->
                    DeviceManufacturer.XIAOMI to DeviceBrand.XIAOMI

                // Transsion - Tested
                "tecno" in manufacturer || "tecno" in brand || "tecno" in model ->
                    DeviceManufacturer.TRANSSION to DeviceBrand.TECNO

                "infinix" in manufacturer || "infinix" in brand || "infinix" in model ->
                    DeviceManufacturer.TRANSSION to DeviceBrand.INFINIX

                "itel" in manufacturer || "itel" in brand || "itel" in model ->
                    DeviceManufacturer.TRANSSION to DeviceBrand.ITEL

                // Oppo - Partially tested
                "oppo" in manufacturer || "oppo" in brand ->
                    DeviceManufacturer.BBK to DeviceBrand.OPPO

                "vivo" in manufacturer || "vivo" in brand ->
                    DeviceManufacturer.BBK to DeviceBrand.VIVO

                "iqoo" in manufacturer || "iqoo" in brand ->
                    DeviceManufacturer.BBK to DeviceBrand.IQOO

                "realme" in manufacturer || "realme" in brand ->
                    DeviceManufacturer.BBK to DeviceBrand.REALME

                "oneplus" in manufacturer || "oneplus" in brand || "1+" in brand || "1+" in manufacturer ->
                    DeviceManufacturer.BBK to DeviceBrand.ONEPLUS

                // Nothing - Tested
                "nothing" in brand || "nothing" in manufacturer ->
                    DeviceManufacturer.NOTHING to DeviceBrand.NOTHING

                // Samsung - Tested
                "samsung" in manufacturer || "samsung" in brand || "sm-" in model ->
                    DeviceManufacturer.SAMSUNG to DeviceBrand.SAMSUNG

                // Google - Tested
                "google" in manufacturer || "google" in brand || "pixel" in model ->
                    DeviceManufacturer.GOOGLE to DeviceBrand.GOOGLE

                // Huawei Family
                "honor" in brand || "honor" in model ->
                    DeviceManufacturer.HUAWEI to DeviceBrand.HONOR

                "huawei" in brand ->
                    DeviceManufacturer.HUAWEI to DeviceBrand.HUAWEI

                // Sony
                "sony" in brand || "xperia" in model ->
                    DeviceManufacturer.SONY to DeviceBrand.SONY

                // LG
                "lge" in brand || "lg-" in model ->
                    DeviceManufacturer.LG to DeviceBrand.LG

                // Motorola
                "motorola" in brand || "moto" in brand ->
                    DeviceManufacturer.MOTOROLA to DeviceBrand.MOTOROLA

                // Nokia (HMD Global)
                "nokia" in brand || "hmd global" in manufacturer ->
                    DeviceManufacturer.NOKIA to DeviceBrand.NOKIA

                // Asus
                "asus" in brand ->
                    DeviceManufacturer.ASUS to DeviceBrand.ASUS

                // HTC
                "htc" in brand ->
                    DeviceManufacturer.HTC to DeviceBrand.HTC

                // Lenovo
                "lenovo" in brand ->
                    DeviceManufacturer.LENOVO to DeviceBrand.LENOVO

                // TCL
                "tcl" in brand || "tcl" in model ->
                    DeviceManufacturer.TCL to DeviceBrand.TCL

                // ZTE
                "zte" in brand || "zte" in model ->
                    DeviceManufacturer.ZTE to DeviceBrand.ZTE

                // Fairphone
                "fairphone" in brand || "fairphone" in model ->
                    DeviceManufacturer.FAIRPHONE to DeviceBrand.FAIRPHONE

                // Essential
                "essential" in brand || "ph-1" in model ->
                    DeviceManufacturer.ESSENTIAL to DeviceBrand.ESSENTIAL

                // Amazon
                "amazon" in brand || "fire" in model ->
                    DeviceManufacturer.AMAZON to DeviceBrand.AMAZON

                // Microsoft
                "microsoft" in brand || "surface" in model ->
                    DeviceManufacturer.MICROSOFT to DeviceBrand.MICROSOFT

                // BlackBerry
                "blackberry" in brand || "bb" in model ->
                    DeviceManufacturer.BLACKBERRY to DeviceBrand.BLACKBERRY

                else -> DeviceManufacturer.UNKNOWN to DeviceBrand.UNKNOWN
            }
        }

        private fun detectOS(): DeviceOS {
            // Xiaomi - HyperOS
            // Example value: 1
            val hyperOsVersionCode = propertyReader.getProp("ro.mi.os.version.code")?.lowercase()
            // Example value: OS1.0
            val hyperOsVersionName = propertyReader.getProp("ro.mi.os.version.name")?.lowercase()
            // Example value: OS1.0.9.0.TKCINXM
            val hyperOsIncrementalVersion =
                propertyReader.getProp("ro.mi.os.version.incremental")?.lowercase()
            // Xiaomi - MIUI
            // Example value: 14
            val miuiVersionCode = propertyReader.getProp("ro.miui.ui.version.code")?.lowercase()
            // Example value: V140
            val miuiVersionName = propertyReader.getProp("ro.miui.ui.version.name")?.lowercase()

            // Transsion - HiOS, XOS
            // Example value: hios (for HiOS), xos (for XOS)
            val tranosType = propertyReader.getProp("ro.tranos.type")?.lowercase()
            // Example value: hios14.0.1 (for HiOS), xos13.0.0 (for XOS)
            val tranosVersion = propertyReader.getProp("ro.tranos.version")?.lowercase()

            // BBK - Oppo, Vivo, Realme, Oneplus, IQOO
            // Example: [CPHNNNN] in ColorOS, [RMXNNNN] in RealmeUI, [OnePlusMMM] in OnePlus
            val colorOSProduct = propertyReader.getProp("ro.build.product")?.lowercase()
            // Realme
            // Example value: realmeUI (only available in realme UI)
            val realmeOsType = propertyReader.getProp("ro.product.brand.ui")?.lowercase()
            // Example value: [V4.0]
            val realmeUiVersion = propertyReader.getProp("ro.build.version.realmeui")?.lowercase()
            // Oneplus
            // Example value: V12.1
            val oneplusOsVersion = propertyReader.getProp("ro.build.version.oplusrom")?.lowercase()
            // IQO
            // Example: Funtouch
            val vivoOSName = propertyReader.getProp("ro.vivo.os.name")?.lowercase()
            val vivoOSVersion = propertyReader.getProp("ro.vivo.os.version")?.lowercase()

            // Nothing
            // Example value: Nothing OS (3)
            val nothingOsVersion = propertyReader.getProp("ro.build.nothing.version")?.lowercase()

            // Samsung
            val oneUIVersion = propertyReader.getProp("ro.build.version.oneui")?.lowercase()

            return when {
                // Xiaomi - Tested
                hyperOsVersionCode != null && hyperOsVersionName != null && hyperOsIncrementalVersion != null -> DeviceOS.XIAOMI_HYPEROS
                miuiVersionCode != null && miuiVersionName != null -> DeviceOS.XIAOMI_MIUI

                // Transsion - Tested
                tranosVersion != null && tranosType != null && ("hios" in tranosType || "hios" in tranosVersion) -> DeviceOS.TRANSSION_HIOS
                tranosVersion != null && tranosType != null && ("xos" in tranosType || "xos" in tranosVersion) -> DeviceOS.TRANSSION_XOS

                // BBK - Tested
                realmeOsType != null && realmeUiVersion != null && colorOSProduct != null && ("rmx" in colorOSProduct || "realmeui" in realmeOsType) -> DeviceOS.BBK_REALME_UI
                colorOSProduct != null && "cph" in colorOSProduct -> DeviceOS.BBK_COLOR_OS
                vivoOSVersion != null && vivoOSName != null && "funtouch" in vivoOSName -> DeviceOS.BBK_FUNTOUCH_OS
                oneplusOsVersion != null -> DeviceOS.BBK_ONEPLUS_OS

                // Nothing - Tested
                nothingOsVersion != null -> DeviceOS.NOTHING_OS

                // Samsung - Tested
                oneUIVersion != null -> DeviceOS.SAMSUNG_ONE_UI

                // All other OS - Fallback - Tested
                else -> DeviceOS.UNKNOWN
            }
        }
    }
}
