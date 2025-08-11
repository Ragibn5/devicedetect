package com.ragibn5.devicedetect

import android.os.Build

data class DeviceVendor(
    val manufacturer: DeviceManufacturer,
    val brand: DeviceBrand
) {
    companion object {
        fun detect(): DeviceVendor {
            val brand = Build.BRAND.lowercase()
            val model = Build.MODEL.lowercase()
            val manufacturer = Build.MANUFACTURER.lowercase()

            return when {
                // Samsung
                "samsung" in brand || "sm-" in model -> DeviceVendor(
                    DeviceManufacturer.Samsung,
                    DeviceBrand.Samsung
                )

                // Xiaomi Family
                "redmi" in brand || "redmi" in model -> DeviceVendor(
                    DeviceManufacturer.Xiaomi,
                    DeviceBrand.Redmi
                )

                "poco" in brand || "poco" in model -> DeviceVendor(
                    DeviceManufacturer.Xiaomi,
                    DeviceBrand.Poco
                )

                "xiaomi" in brand || "mi " in model -> DeviceVendor(
                    DeviceManufacturer.Xiaomi,
                    DeviceBrand.Xiaomi
                )

                // Huawei Family
                "honor" in brand || "honor" in model -> DeviceVendor(
                    DeviceManufacturer.Huawei,
                    DeviceBrand.Honor
                )

                "huawei" in brand -> DeviceVendor(
                    DeviceManufacturer.Huawei,
                    DeviceBrand.Huawei
                )

                // Oppo Family
                "oneplus" in brand || "1+" in brand -> DeviceVendor(
                    DeviceManufacturer.Oppo,
                    DeviceBrand.OnePlus
                )

                "realme" in brand -> DeviceVendor(
                    DeviceManufacturer.Oppo,
                    DeviceBrand.Realme
                )

                "oppo" in brand || "cph" in model -> DeviceVendor(
                    DeviceManufacturer.Oppo,
                    DeviceBrand.Oppo
                )

                // Vivo Family
                "iqoo" in brand || "iqoo" in model -> DeviceVendor(
                    DeviceManufacturer.Vivo,
                    DeviceBrand.iQOO
                )

                "vivo" in brand -> DeviceVendor(
                    DeviceManufacturer.Vivo,
                    DeviceBrand.Vivo
                )

                // Google
                "google" in brand || "pixel" in model -> DeviceVendor(
                    DeviceManufacturer.Google,
                    DeviceBrand.Google
                )

                // Sony
                "sony" in brand || "xperia" in model -> DeviceVendor(
                    DeviceManufacturer.Sony,
                    DeviceBrand.Sony
                )

                // LG
                "lge" in brand || "lg-" in model -> DeviceVendor(
                    DeviceManufacturer.LG,
                    DeviceBrand.LG
                )

                // Motorola
                "motorola" in brand || "moto" in brand -> DeviceVendor(
                    DeviceManufacturer.Motorola,
                    DeviceBrand.Motorola
                )

                // Nokia (HMD Global)
                "nokia" in brand || "hmd global" in manufacturer -> DeviceVendor(
                    DeviceManufacturer.Nokia,
                    DeviceBrand.Nokia
                )

                // Asus
                "asus" in brand -> DeviceVendor(
                    DeviceManufacturer.Asus,
                    DeviceBrand.Asus
                )

                // HTC
                "htc" in brand -> DeviceVendor(
                    DeviceManufacturer.HTC,
                    DeviceBrand.HTC
                )

                // Lenovo
                "lenovo" in brand -> DeviceVendor(
                    DeviceManufacturer.Lenovo,
                    DeviceBrand.Lenovo
                )

                // Nothing
                "nothing" in brand -> DeviceVendor(
                    DeviceManufacturer.Nothing,
                    DeviceBrand.Nothing
                )

                // Tecno Family (Transsion Holdings)
                "tecno" in brand || "tecno" in model -> DeviceVendor(
                    DeviceManufacturer.Transsion,
                    DeviceBrand.Tecno
                )

                "infinix" in brand || "infinix" in model -> DeviceVendor(
                    DeviceManufacturer.Transsion,
                    DeviceBrand.Infinix
                )

                "itel" in brand || "itel" in model -> DeviceVendor(
                    DeviceManufacturer.Transsion,
                    DeviceBrand.Itel
                )

                // TCL
                "tcl" in brand || "tcl" in model -> DeviceVendor(
                    DeviceManufacturer.TCL,
                    DeviceBrand.TCL
                )

                // ZTE
                "zte" in brand || "zte" in model -> DeviceVendor(
                    DeviceManufacturer.ZTE,
                    DeviceBrand.ZTE
                )

                // Fairphone
                "fairphone" in brand || "fairphone" in model -> DeviceVendor(
                    DeviceManufacturer.Fairphone,
                    DeviceBrand.Fairphone
                )

                // Essential
                "essential" in brand || "ph-1" in model -> DeviceVendor(
                    DeviceManufacturer.Essential,
                    DeviceBrand.Essential
                )

                // Amazon
                "amazon" in brand || "fire" in model -> DeviceVendor(
                    DeviceManufacturer.Amazon,
                    DeviceBrand.Amazon
                )

                // Microsoft
                "microsoft" in brand || "surface" in model -> DeviceVendor(
                    DeviceManufacturer.Microsoft,
                    DeviceBrand.Microsoft
                )

                // BlackBerry
                "blackberry" in brand || "bb" in model -> DeviceVendor(
                    DeviceManufacturer.BlackBerry,
                    DeviceBrand.BlackBerry
                )

                else -> DeviceVendor(
                    DeviceManufacturer.Unknown,
                    DeviceBrand.Unknown
                )
            }
        }
    }
}