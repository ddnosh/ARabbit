package com.androidwind.androidquick.util

import java.io.Closeable
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class IOUtil private constructor() {

    init {
        throw AssertionError()
    }

    companion object {


        /**
         * Close closable object and wrap [IOException] with [ ]
         *
         * @param closeable closeable object
         */
        fun close(closeable: Closeable?) {
            if (closeable != null) {
                try {
                    closeable.close()
                } catch (e: IOException) {
                    throw RuntimeException("IOException occurred. ", e)
                }
            }
        }


        /**
         * Close closable and hide possible [IOException]
         *
         * @param closeable closeable object
         */
        fun closeQuietly(closeable: Closeable?) {
            if (closeable != null) {
                try {
                    closeable.close()
                } catch (e: IOException) {
                    // Ignored
                }
            }
        }


        /**
         * 保存文本
         * @param fileName  文件名字
         * @param content  内容
         * @param append  是否累加
         * @return  是否成功
         */
        fun saveTextValue(fileName: String, content: String, append: Boolean): Boolean {

            try {
                val textFile = File(fileName)
                if (!append && textFile.exists()) textFile.delete()

                val os = FileOutputStream(textFile)
                os.write(content.toByteArray(charset("UTF-8")))
                os.close()
            } catch (ee: Exception) {
                return false
            }

            return true
        }


        /**
         * 删除目录下所有文件
         * @param Path    路径
         */
        fun deleteAllFile(Path: String) {

            // 删除目录下所有文件
            val path = File(Path)
            val files = path.listFiles()
            if (files != null) {
                for (tfi in files) {
                    if (tfi.isDirectory) {
                        println(tfi.name)
                    } else {
                        tfi.delete()
                    }
                }
            }
        }
    }
}
