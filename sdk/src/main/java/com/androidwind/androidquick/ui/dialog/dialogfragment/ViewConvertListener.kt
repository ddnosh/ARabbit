package com.androidwind.androidquick.ui.dialog.dialogfragment

import java.io.Serializable

interface ViewConvertListener : Serializable {

    fun convertView(holder: ViewHolder, dialog: BaseDialogFragment)

    companion object {
        val serialVersionUID by lazy { System.currentTimeMillis() }
    }
}
