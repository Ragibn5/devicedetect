package com.ragibn5.devicedetect

import DeviceOS
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
            val os = detectOS(detectedManufacturer, detectedBrand)

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
                /// VERIFIED
                // Xiaomi Family
                "redmi" in brand || "redmi" in model ->
                    DeviceManufacturer.XIAOMI to DeviceBrand.REDMI

                "poco" in brand || "poco" in model ->
                    DeviceManufacturer.XIAOMI to DeviceBrand.POCO

                "xiaomi" in brand || "mi " in model ->
                    DeviceManufacturer.XIAOMI to DeviceBrand.XIAOMI

                // Tecno Family (Transsion Holdings)
                "tecno" in brand || "tecno" in model ->
                    DeviceManufacturer.TRANSSION to DeviceBrand.TECNO

                "infinix" in brand || "infinix" in model ->
                    DeviceManufacturer.TRANSSION to DeviceBrand.INFINIX

                "itel" in brand || "itel" in model ->
                    DeviceManufacturer.TRANSSION to DeviceBrand.ITEL

                // Oppo Family
                "oneplus" in brand || "1+" in brand ->
                    DeviceManufacturer.BBK to DeviceBrand.ONEPLUS

                // Nothing
                "nothing" in brand ->
                    DeviceManufacturer.NOTHING to DeviceBrand.NOTHING


                /// UNVERIFIED
                // Samsung
                "samsung" in brand || "sm-" in model ->
                    DeviceManufacturer.SAMSUNG to DeviceBrand.SAMSUNG

                // Huawei Family
                "honor" in brand || "honor" in model ->
                    DeviceManufacturer.HUAWEI to DeviceBrand.HONOR

                "huawei" in brand ->
                    DeviceManufacturer.HUAWEI to DeviceBrand.HUAWEI

                // Oppo Family
                "realme" in brand ->
                    DeviceManufacturer.BBK to DeviceBrand.REALME

                "oppo" in brand || "cph" in model ->
                    DeviceManufacturer.BBK to DeviceBrand.OPPO

                "iqoo" in brand || "iqoo" in model ->
                    DeviceManufacturer.BBK to DeviceBrand.IQOO

                "vivo" in brand ->
                    DeviceManufacturer.BBK to DeviceBrand.VIVO

                // Google
                "google" in brand || "pixel" in model ->
                    DeviceManufacturer.GOOGLE to DeviceBrand.GOOGLE

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

        private fun detectOS(
            manufacturer: DeviceManufacturer,
            detectedBrand: DeviceBrand,
        ): DeviceOS {
            // Xiaomi - HyperOS
            // Example value: 1
            val hyperOsVersionCode = propertyReader.getProp("ro.mi.os.version.code")
            // Example value: OS1.0
            val hyperOsVersionName = propertyReader.getProp("ro.mi.os.version.name")
            // Example value: OS1.0.9.0.TKCINXM
            val hyperOsIncrementalVersion = propertyReader.getProp("ro.mi.os.version.incremental")
            // Xiaomi - MIUI
            // Example value: 14
            val miuiVersionCode = propertyReader.getProp("ro.miui.ui.version.code")
            // Example value: V140
            val miuiVersionName = propertyReader.getProp("ro.miui.ui.version.name")

            // Transsion - HiOS, Infinix
            // Example value: hios (for HiOS), xos (for XOS)
            val tranosType = propertyReader.getProp("ro.tranos.type")
            // Example value: hios14.0.1 (for HiOS), xos13.0.0 (for XOS)
            val tranosVersion = propertyReader.getProp("ro.tranos.version")

            // BBK - Oppo, Vivo, Realme, Oneplus, IQOO
            // Realme
            // Example value: realme
            val realmeBrandSubCategory = propertyReader.getProp("ro.product.brand.sub")
            // Example value: realmeUI
            val realmeOsType = propertyReader.getProp("ro.product.brand.ui")
            // Oneplus
            // Example value: V12.1
            val oneplusOsVersion = propertyReader.getProp("ro.build.version.oplusrom")

            // Nothing
            // Example value: Nothing OS (3)
            val nothingOsVersion = propertyReader.getProp("ro.build.nothing.version")

            return when (manufacturer) {
                DeviceManufacturer.XIAOMI -> when {
                    hyperOsVersionCode != null && hyperOsVersionName != null && hyperOsIncrementalVersion != null -> DeviceOS.XIAOMI_HYPEROS
                    miuiVersionCode != null && miuiVersionName != null -> DeviceOS.XIAOMI_MIUI
                    else -> DeviceOS.UNKNOWN
                }

                DeviceManufacturer.TRANSSION -> when (tranosType?.lowercase()) {
                    "hios" -> DeviceOS.TRANSSION_HIOS
                    "xos" -> DeviceOS.TRANSSION_XOS
                    else -> DeviceOS.UNKNOWN
                }

                DeviceManufacturer.BBK -> when (realmeOsType?.lowercase()) {
                    "realmeui" -> DeviceOS.BBK_REALME_UI
                    else -> when {
                        oneplusOsVersion != null -> DeviceOS.BBK_ONEPLUS_OS
                        else -> DeviceOS.UNKNOWN
                    }
                }

                DeviceManufacturer.NOTHING -> when {
                    nothingOsVersion != null -> DeviceOS.NOTHING_OS
                    else -> DeviceOS.UNKNOWN
                }

                else -> DeviceOS.UNKNOWN
            }
        }
    }
}
