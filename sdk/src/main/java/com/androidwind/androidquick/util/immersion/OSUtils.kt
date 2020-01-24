package com.androidwind.androidquick.util.immersion

import android.os.Build
import android.text.TextUtils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object OSUtils {
    val ROM_MIUI = "MIUI"
    val ROM_EMUI = "EMUI"
    val ROM_FLYME = "FLYME"
    val ROM_OPPO = "OPPO"
    val ROM_SMARTISAN = "SMARTISAN"
    val ROM_VIVO = "VIVO"
    val ROM_QIKU = "QIKU"
    private val KEY_VERSION_MIUI = "ro.miui.ui.version.name"
    private val KEY_VERSION_EMUI = "ro.build.version.emui"
    private val KEY_VERSION_OPPO = "ro.build.version.opporom"
    private val KEY_VERSION_SMARTISAN = "ro.smartisan.version"
    private val KEY_VERSION_VIVO = "ro.vivo.os.version"
    private var sName: String? = null
    private var sVersion: String? = null

    @JvmStatic
    val isEmui: Boolean = check(ROM_EMUI)
    @JvmStatic
    val isMiui: Boolean = check(ROM_MIUI)
    @JvmStatic
    val isVivo: Boolean = check(ROM_VIVO)
    @JvmStatic
    val isOppo: Boolean = check(ROM_OPPO)
    @JvmStatic
    val isFlyme: Boolean = check(ROM_FLYME)
    @JvmStatic
    val is360: Boolean = check(ROM_QIKU) || check("360")
    @JvmStatic
    val isSmartisan: Boolean = check(ROM_SMARTISAN)

    @JvmStatic
    val name: String?
        get() {
            if (sName == null) {
                check("")
            }
            return sName
        }

    @JvmStatic
    val version: String?
        get() {
            if (sVersion == null) {
                check("")
            }
            return sVersion
        }

    @JvmStatic
    fun check(rom: String): Boolean {
        if (sName != null) {
            return sName == rom
        }
        if (!TextUtils.isEmpty(getProp(KEY_VERSION_MIUI))) {
            sVersion = getProp(KEY_VERSION_MIUI)
            sName = ROM_MIUI
        } else if (!TextUtils.isEmpty(getProp(KEY_VERSION_EMUI))) {
            sVersion = getProp(KEY_VERSION_EMUI)
            sName = ROM_EMUI
        } else if (!TextUtils.isEmpty(getProp(KEY_VERSION_OPPO))) {
            sVersion = getProp(KEY_VERSION_OPPO)
            sName = ROM_OPPO
        } else if (!TextUtils.isEmpty(getProp(KEY_VERSION_VIVO))) {
            sVersion = getProp(KEY_VERSION_VIVO)
            sName = ROM_VIVO
        } else if (!TextUtils.isEmpty(getProp(KEY_VERSION_SMARTISAN))) {
            sVersion = getProp(KEY_VERSION_SMARTISAN)
            sName = ROM_SMARTISAN
        } else {
            sVersion = Build.DISPLAY
            if (sVersion!!.toUpperCase().contains(ROM_FLYME)) {
                sName = ROM_FLYME
            } else {
                sVersion = Build.UNKNOWN
                sName = Build.MANUFACTURER.toUpperCase()
            }
        }
        return sName == rom
    }

    @JvmStatic
    fun getProp(name: String): String? {
        var line: String? = null
        var input: BufferedReader? = null
        try {
            val p = Runtime.getRuntime().exec("getprop $name")
            input = BufferedReader(InputStreamReader(p.inputStream), 1024)
            line = input.readLine()
            input.close()
        } catch (ex: IOException) {
            return null
        } finally {
            if (input != null) {
                try {
                    input.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return line
    }
}