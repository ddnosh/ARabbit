package com.androidwind.androidquick.util

import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.webkit.CookieManager
import android.webkit.CookieSyncManager
import android.webkit.MimeTypeMap
import android.widget.Toast

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.util.ArrayList

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object FileUtil {

    val TAG = "FileUtils"
    val DIR = ".AndroidQuick"
    val CUSPATH = Environment.getExternalStorageDirectory().path + File.separator + DIR + File.separator
    private val HTTP_CACHE_DIR_NAME = "http_response"

    // 遍历接收一个文件路径，然后把文件子目录中的所有文件遍历并输出来
    @JvmStatic
    private fun getAllFiles(root: File) {
        val files = root.listFiles()
        if (files != null) {
            for (f in files) {
                if (f.isDirectory) {
                    getAllFiles(f)
                } else {
                    println(f)
                }
            }
        }
    }

    /**
     * 获取sd卡状态
     *
     * @return
     */
    @JvmStatic
    val sdCardStatus: Boolean
        get() {
            val status = Environment.getExternalStorageState()
            return status == Environment.MEDIA_MOUNTED
        }

    /**
     * 创建sd卡根目录文件夹
     *
     * @return
     */
    @JvmStatic
    fun createSDCardDir(): String? {
        if (Environment.MEDIA_MOUNTED == Environment
                .getExternalStorageState()) {
            val sdcardDir = Environment.getExternalStorageDirectory()
            val path = sdcardDir.path + File.separator + DIR
            val path1 = File(path)
            if (!path1.exists()) {
                path1.mkdirs()
            }
            return path
        }
        return null
    }


    /**
     * 判断文件是否存在
     *
     * @param path 文件路径
     * @return
     */
    @JvmStatic
    fun isExistFile(path: String): Boolean {
        return File(path).exists()
    }

    /**
     * 读取setting.properties文本文件中的内容
     *
     * @param strFilePath 文件目录
     * @param key         索引内容健
     * @return
     */
    @JvmStatic
    fun ReadPropertiesFile(strFilePath: String, key: String): String {
        val path = strFilePath + File.separator + "setting.properties"
        var content = "" //文件内容字符串
        //打开文件
        val file = File(path)
        //如果path是传递过来的参数，可以做一个非目录的判断
        if (file.isDirectory) {
            LogUtil.d(TAG, "The File doesn't not exist.")
        } else {
            try {
                val instream = FileInputStream(file)
                if (instream != null) {
                    val inputreader = InputStreamReader(instream)
                    val buffreader = BufferedReader(inputreader)
                    var line: String? = null
                    //分行读取
                    while (({ line = buffreader.readLine();line }) != null) {
                        content += line + "\n"
                    }
                    instream.close()
                }
            } catch (e: java.io.FileNotFoundException) {
                LogUtil.d(TAG, "The File doesn't not exist.")
            } catch (e: IOException) {
                e.message?.let { LogUtil.d(TAG, it) }
            }
        }
        val contents = content.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in contents.indices) {
            val value = contents[i]
            if (value.contains(key)) {
                return value.replace(key, "")
            }
        }
        return ""
    }

    /**
     * 读取setting.properties文本文件中的内容
     *
     * @param strFilePath 文件目录
     * @return
     */
    @JvmStatic
    fun ReadPropertiesFile(strFilePath: String): String {
        var content = "" //文件内容字符串
        //打开文件
        val file = File(strFilePath)
        //如果path是传递过来的参数，可以做一个非目录的判断
        if (file.isDirectory) {
            LogUtil.d("TestFile", "The File doesn't not exist.")
        } else {
            try {
                val instream = FileInputStream(file)
                if (instream != null) {
                    val inputreader = InputStreamReader(instream)
                    val buffreader = BufferedReader(inputreader)
                    var line: String? = null
                    //分行读取
                    while (({ line = buffreader.readLine();line }) != null) {
                        content += line + "\n"
                    }
                    instream.close()
                }
            } catch (e: java.io.FileNotFoundException) {
                LogUtil.d(TAG, "The File doesn't not exist.")
            } catch (e: IOException) {
                e.message?.let { LogUtil.d(TAG, it) }
            }
        }

        return content
    }

    @JvmStatic
    fun getFileType(fileName: String): String {
        return fileName.substring(fileName.lastIndexOf(".") + 1)
    }

    /**
     * 安装APK文件
     */
    @JvmStatic
    fun installApk(context: Context, savePath: String) {
        LogUtil.d(TAG, "savePath $savePath")
        val apkfile = File(savePath)
        if (!apkfile.exists()) {
            return
        }
        // 通过Intent安装APK文件
        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.setDataAndType(Uri.parse("file://$apkfile"), "application/vnd.android.package-archive")
        context.startActivity(intent)
    }

    //    public static void assetsDataToSD(Context context,String fileName) throws IOException
    //    {
    //        InputStream myInput;
    //        //文件夹
    //        Logger.d(TAG,"path--> "+fileName);
    //        OutputStream myOutput = new FileOutputStream(fileName);
    //        myInput = context.getAssets().open("card_base.zip");
    //        byte[] buffer = new byte[1024];
    //        int length = myInput.read(buffer);
    //        while(length > 0)
    //        {
    //            myOutput.write(buffer, 0, length);
    //            length = myInput.read(buffer);
    //        }
    //
    //        myOutput.flush();
    //        myInput.close();
    //        myOutput.close();
    //    }

    @Throws(IOException::class)
    @JvmStatic
    fun assetsDataToSD(context: Context, fileName: String) {
        assetsDataToSD(context, "card_base.zip", fileName)
    }

    @Throws(IOException::class)
    @JvmStatic
    fun assetsDataToSD(context: Context, assetsDataName: String, fileName: String) {
        val myInput: InputStream
        //文件夹
        LogUtil.d(TAG, "path--> $fileName")
        if (!File(fileName).parentFile!!.exists()) {
            File(fileName).parentFile!!.mkdirs()
        }
        val myOutput = FileOutputStream(fileName)
        myInput = context.assets.open(assetsDataName)
        val buffer = ByteArray(1024)
        var length = myInput.read(buffer)
        while (length > 0) {
            myOutput.write(buffer, 0, length)
            length = myInput.read(buffer)
        }

        myOutput.flush()
        myInput.close()
        myOutput.close()
    }

    @JvmStatic
    fun insertCardRerousce(srcFile: String, tarFile: String) {
        val sourceFile = File(srcFile)
        val targetFile = File(tarFile)
        copyFile(sourceFile, targetFile)
    }

    // 复制文件
    @JvmStatic
    fun copyFile(source: File, target: File): Boolean {
        var outputStream: FileOutputStream? = null
        var inputStream: FileInputStream? = null
        try {
            inputStream = FileInputStream(source)
            outputStream = FileOutputStream(target)
            val bytes = ByteArray(1024)
            var read: Int = -1
            while ({ read = inputStream.read(bytes); read }() != -1) {
                outputStream.write(bytes, 0, read)
            }
            outputStream.flush()
            return true
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return false
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        } finally {
            try {
                outputStream!!.close()
                inputStream!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    @JvmStatic
    fun getMd5Str(path: String): String {
        var md5 = ""
        try {
            val file = File(path)
            md5 = file.name
            if (file.exists()) {
                if (file.isDirectory) {
                    for (c in file.listFiles()!!) {
                        md5 += getMd5Str(c.absolutePath)
                    }
                } else {
                    md5 += file.name
                }
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

        return md5
    }

    @JvmStatic
    fun clearCookies(context: Context) {
        // Edge case: an illegal state exception is thrown if an instance of
        // CookieSyncManager has not be created.  CookieSyncManager is normally
        // created by a WebKit view, but this might happen if you start the
        // app, restore saved state, and click logout before running a UI
        // dialog in a WebView -- in which case the app crashes
        val cookieSyncMngr = CookieSyncManager.createInstance(context)
        val cookieManager = CookieManager.getInstance()
        cookieManager.removeAllCookie()
    }

    val FILE_EXTENSION_SEPARATOR = "."
    /**
     * URI类型：file
     */
    val URI_TYPE_FILE = "file"


    /**
     * read file
     *
     * @param filePath    路径
     * @param charsetName The name of a supported [                    ]
     * @return if file not exist, return null, else return content of file
     * @throws RuntimeException if an error occurs while operator
     * BufferedReader
     */
    @JvmStatic
    fun readFile(filePath: String, charsetName: String): StringBuilder? {

        val file = File(filePath)
        val fileContent = StringBuilder("")
        if (file == null || !file.isFile) {
            return null
        }

        var reader: BufferedReader? = null
        try {
            val `is` = InputStreamReader(
                FileInputStream(file), charsetName)
            reader = BufferedReader(`is`)
            var line: String? = null
            while (({ line = reader.readLine();line }) != null) {
                if (fileContent.toString() != "") {
                    fileContent.append("\r\n")
                }
                fileContent.append(line)
            }
            return fileContent
        } catch (e: IOException) {
            throw RuntimeException("IOException occurred. ", e)
        } finally {
            IOUtil.close(reader)
        }
    }


    /**
     * write file
     *
     * @param filePath 路径
     * @param content  上下文
     * @param append   is append, if true, write to the end of file, else clear
     * content of file and write into it
     * @return return false if content is empty, true otherwise
     * @throws RuntimeException if an error occurs while operator FileWriter
     */
    @JvmOverloads
    @JvmStatic
    fun writeFile(filePath: String, content: String, append: Boolean = false): Boolean {

        if (StringUtil.isEmpty(content)) {
            return false
        }

        var fileWriter: FileWriter? = null
        try {
            makeDirs(filePath)
            fileWriter = FileWriter(filePath, append)
            fileWriter.write(content)
            return true
        } catch (e: IOException) {
            throw RuntimeException("IOException occurred. ", e)
        } finally {
            IOUtil.close(fileWriter)
        }
    }


    /**
     * write file
     *
     * @param filePath    路径
     * @param contentList 集合
     * @param append      is append, if true, write to the end of file, else clear
     * content of file and write into it
     * @return return false if contentList is empty, true otherwise
     * @throws RuntimeException if an error occurs while operator FileWriter
     */
    @JvmOverloads
    @JvmStatic
    fun writeFile(filePath: String, contentList: List<String>, append: Boolean = false): Boolean {

        if (contentList.size == 0 || null == contentList) {
            return false
        }

        var fileWriter: FileWriter? = null
        try {
            makeDirs(filePath)
            fileWriter = FileWriter(filePath, append)
            var i = 0
            for (line in contentList) {
                if (i++ > 0) {
                    fileWriter.write("\r\n")
                }
                fileWriter.write(line)
            }
            return true
        } catch (e: IOException) {
            throw RuntimeException("IOException occurred. ", e)
        } finally {
            IOUtil.close(fileWriter)
        }
    }


    /**
     * write file
     *
     * @param filePath 路径
     * @param stream   the input stream
     * @param append   if `true`, then bytes will be written to the
     * end
     * of the file rather than the beginning
     * @return return true
     * FileOutputStream
     */
    @JvmOverloads
    @JvmStatic
    fun writeFile(filePath: String?, stream: InputStream, append: Boolean = false): Boolean {

        return writeFile(if (filePath != null) File(filePath) else null, stream,
            append)
    }


    /**
     * write file
     *
     * @param file   the file to be opened for writing.
     * @param stream the input stream
     * @param append if `true`, then bytes will be written to the
     * end
     * of the file rather than the beginning
     * @return return true
     * @throws RuntimeException if an error occurs while operator
     * FileOutputStream
     */
    @JvmOverloads
    @JvmStatic
    fun writeFile(file: File?, stream: InputStream, append: Boolean = false): Boolean {
        var o: OutputStream? = null
        try {
            makeDirs(file?.absolutePath)
            o = FileOutputStream(file, append)
            val data = ByteArray(1024)
            var length = -1
            while ({ length = stream.read(data); length }() != -1) {
                o.write(data, 0, length)
            }
            o.flush()
            return true
        } catch (e: FileNotFoundException) {
            throw RuntimeException("FileNotFoundException occurred. ", e)
        } catch (e: IOException) {
            throw RuntimeException("IOException occurred. ", e)
        } finally {
            IOUtil.close(o)
            IOUtil.close(stream)
        }
    }

    /**
     * 写入文件
     *
     * @param in
     * @param file
     */
    @Throws(IOException::class)
    @JvmStatic
    fun writeFile(`in`: InputStream, file: File) {
        if (!file.parentFile!!.exists())
            file.parentFile!!.mkdirs()

        if (file != null && file.exists())
            file.delete()

        val out = FileOutputStream(file)
        val buffer = ByteArray(1024 * 128)
        var len = -1
        while ({ len = `in`.read(buffer); len }() != -1) {
            out.write(buffer, 0, len)
        }
        out.flush()
        out.close()
        `in`.close()
    }


    /**
     * move file
     *
     * @param sourceFilePath 资源路径
     * @param destFilePath   删除的路径
     */
    @JvmStatic
    fun moveFile(sourceFilePath: String, destFilePath: String) {

        if (TextUtils.isEmpty(sourceFilePath) || TextUtils.isEmpty(destFilePath)) {
            throw RuntimeException(
                "Both sourceFilePath and destFilePath cannot be null.")
        }
        moveFile(File(sourceFilePath), File(destFilePath))
    }


    /**
     * move file
     *
     * @param srcFile  文件对象
     * @param destFile 对象
     */
    @JvmStatic
    fun moveFile(srcFile: File, destFile: File) {

        val rename = srcFile.renameTo(destFile)
        if (!rename) {
            copyFile(srcFile.absolutePath, destFile.absolutePath)
            deleteFile(srcFile.absolutePath)
        }
    }


    /**
     * copy file
     *
     * @param sourceFilePath 资源路径
     * @param destFilePath   删除的文件
     * @return 返回是否成功
     * @throws RuntimeException if an error occurs while operator
     * FileOutputStream
     */
    @JvmStatic
    fun copyFile(sourceFilePath: String, destFilePath: String): Boolean {

        var inputStream: InputStream? = null
        try {
            inputStream = FileInputStream(sourceFilePath)
        } catch (e: FileNotFoundException) {
            throw RuntimeException("FileNotFoundException occurred. ", e)
        }

        return writeFile(destFilePath, inputStream)
    }


    /**
     * read file to string list, a element of list is a line
     *
     * @param filePath    路径
     * @param charsetName The name of a supported [                    ]
     * @return if file not exist, return null, else return content of file
     * @throws RuntimeException if an error occurs while operator
     * BufferedReader
     */
    @JvmStatic
    fun readFileToList(filePath: String, charsetName: String): List<String>? {

        val file = File(filePath)
        val fileContent = ArrayList<String>()
        if (file == null || !file.isFile) {
            return null
        }

        var reader: BufferedReader? = null
        try {
            val `is` = InputStreamReader(
                FileInputStream(file), charsetName)
            reader = BufferedReader(`is`)
            var line: String = ""
            while (({ line = reader.readLine();line }) != null) {
                fileContent.add(line)
            }
            return fileContent
        } catch (e: IOException) {
            throw RuntimeException("IOException occurred. ", e)
        } finally {
            IOUtil.close(reader)
        }
    }


    /**
     * @param filePath 文件的路径
     * @return 返回文件的信息
     */
    @JvmStatic
    fun getFileNameWithoutExtension(filePath: String): String? {


        if (StringUtil.isEmpty(filePath)) {
            return filePath
        }

        val extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR)
        val filePosi = filePath.lastIndexOf(File.separator)
        if (filePosi == -1) {
            return if (extenPosi == -1)
                filePath
            else
                filePath.substring(0, extenPosi)
        }
        if (extenPosi == -1) {
            return filePath.substring(filePosi + 1)
        }
        return if (filePosi < extenPosi)
            filePath.substring(filePosi + 1,
                extenPosi)
        else
            filePath.substring(filePosi + 1)
    }


    /**
     * get file name from path, include suffix
     *
     *
     * <pre>
     * getFileName(null)               =   null
     * getFileName("")                 =   ""
     * getFileName("   ")              =   "   "
     * getFileName("a.mp3")            =   "a.mp3"
     * getFileName("a.b.rmvb")         =   "a.b.rmvb"
     * getFileName("abc")              =   "abc"
     * getFileName("c:\\")              =   ""
     * getFileName("c:\\a")             =   "a"
     * getFileName("c:\\a.b")           =   "a.b"
     * getFileName("c:a.txt\\a")        =   "a"
     * getFileName("/home/admin")      =   "admin"
     * getFileName("/home/admin/a.txt/b.mp3")  =   "b.mp3"
    </pre> *
     *
     * @param filePath 路径
     * @return file name from path, include suffix
     */
    @JvmStatic
    fun getFileName(filePath: String): String? {

        if (StringUtil.isEmpty(filePath)) {
            return filePath
        }

        val filePosi = filePath.lastIndexOf(File.separator)
        return if (filePosi == -1) filePath else filePath.substring(filePosi + 1)
    }


    /**
     * get folder name from path
     *
     *
     * <pre>
     * getFolderName(null)               =   null
     * getFolderName("")                 =   ""
     * getFolderName("   ")              =   ""
     * getFolderName("a.mp3")            =   ""
     * getFolderName("a.b.rmvb")         =   ""
     * getFolderName("abc")              =   ""
     * getFolderName("c:\\")              =   "c:"
     * getFolderName("c:\\a")             =   "c:"
     * getFolderName("c:\\a.b")           =   "c:"
     * getFolderName("c:a.txt\\a")        =   "c:a.txt"
     * getFolderName("c:a\\b\\c\\d.txt")    =   "c:a\\b\\c"
     * getFolderName("/home/admin")      =   "/home"
     * getFolderName("/home/admin/a.txt/b.mp3")  =   "/home/admin/a.txt"
    </pre> *
     *
     * @param filePath 路径
     * @return file name from path, include suffix
     */
    @JvmStatic
    fun getFolderName(filePath: String?): String? {


        if (StringUtil.isEmpty(filePath)) {
            return filePath
        }

        val filePosi = filePath?.lastIndexOf(File.separator)
        return if (filePosi == -1) "" else filePosi?.let { filePath?.substring(0, it) }
    }


    /**
     * get suffix of file from path
     *
     *
     * <pre>
     * getFileExtension(null)               =   ""
     * getFileExtension("")                 =   ""
     * getFileExtension("   ")              =   "   "
     * getFileExtension("a.mp3")            =   "mp3"
     * getFileExtension("a.b.rmvb")         =   "rmvb"
     * getFileExtension("abc")              =   ""
     * getFileExtension("c:\\")              =   ""
     * getFileExtension("c:\\a")             =   ""
     * getFileExtension("c:\\a.b")           =   "b"
     * getFileExtension("c:a.txt\\a")        =   ""
     * getFileExtension("/home/admin")      =   ""
     * getFileExtension("/home/admin/a.txt/b")  =   ""
     * getFileExtension("/home/admin/a.txt/b.mp3")  =   "mp3"
    </pre> *
     *
     * @param filePath 路径
     * @return 信息
     */
    @JvmStatic
    fun getFileExtension(filePath: String): String? {

        if (StringUtil.isBlank(filePath)) {
            return filePath
        }

        val extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR)
        val filePosi = filePath.lastIndexOf(File.separator)
        if (extenPosi == -1) {
            return ""
        }
        return if (filePosi >= extenPosi) "" else filePath.substring(extenPosi + 1)
    }


    /**
     * @param filePath 路径
     * @return 是否创建成功
     */
    @JvmStatic
    fun makeDirs(filePath: String?): Boolean {

        val folderName = getFolderName(filePath)
        if (StringUtil.isEmpty(folderName)) {
            return false
        }

        val folder = File(folderName!!)
        return if (folder.exists() && folder.isDirectory)
            true
        else
            folder.mkdirs()
    }


    /**
     * @param filePath 路径
     * @return 是否创建成功
     */
    @JvmStatic
    fun makeFolders(filePath: String): Boolean {
        return makeDirs(filePath)
    }


    /**
     * @param filePath 路径
     * @return 是否存在这个文件
     */
    @JvmStatic
    fun isFileExist(filePath: String): Boolean {
        if (StringUtil.isBlank(filePath)) {
            return false
        }

        val file = File(filePath)
        return file.exists() && file.isFile
    }


    /**
     * @param directoryPath 路径
     * @return 是否有文件夹
     */
    @JvmStatic
    fun isFolderExist(directoryPath: String): Boolean {

        if (StringUtil.isBlank(directoryPath)) {
            return false
        }

        val dire = File(directoryPath)
        return dire.exists() && dire.isDirectory
    }


    /**
     * @param path 路径
     * @return 是否删除成功
     */
    @JvmStatic
    fun deleteFile(path: String): Boolean {

        if (StringUtil.isBlank(path)) {
            return true
        }

        val file = File(path)
        if (!file.exists()) {
            return true
        }
        if (file.isFile) {
            return file.delete()
        }
        if (!file.isDirectory) {
            return false
        }
        for (f in file.listFiles()!!) {
            if (f.isFile) {
                f.delete()
            } else if (f.isDirectory) {
                deleteFile(f.absolutePath)
            }
        }
        return file.delete()
    }


    /**
     * @param path 路径
     * @return 返回文件大小
     */
    @JvmStatic
    fun getFileSize(path: String): Long {

        if (StringUtil.isBlank(path)) {
            return -1
        }

        val file = File(path)
        return if (file.exists() && file.isFile) file.length() else -1
    }


    /**
     * 保存多媒体数据为文件.
     *
     * @param data     多媒体数据
     * @param fileName 保存文件名
     * @return 保存成功或失败
     */
    @JvmStatic
    fun save2File(data: InputStream, fileName: String): Boolean {
        val file = File(fileName)
        var fos: FileOutputStream? = null
        try {
            // 文件或目录不存在时,创建目录和文件.
            if (!file.exists()) {
                file.parentFile!!.mkdirs()
                file.createNewFile()
            }

            // 写入数据
            fos = FileOutputStream(file)
            val b = ByteArray(1024)
            var len: Int = -1
            while ({ len = data.read(b); len }() != -1) {
                fos.write(b, 0, len)
            }
            fos.close()

            return true
        } catch (ex: IOException) {

            return false
        }
    }


    /**
     * 读取文件的字节数组.
     *
     * @param file 文件
     * @return 字节数组
     */
    @JvmStatic
    fun readFile4Bytes(file: File): ByteArray? {

        // 如果文件不存在,返回空
        if (!file.exists()) {
            return null
        }
        var fis: FileInputStream? = null
        try {
            // 读取文件内容.
            fis = FileInputStream(file)
            val arrData = ByteArray(file.length().toInt())
            fis.read(arrData)
            // 返回
            return arrData
        } catch (e: IOException) {

            return null
        } finally {
            if (fis != null) {
                try {
                    fis.close()
                } catch (e: IOException) {

                }
            }
        }
    }


    /**
     * 读取文本文件内容，以行的形式读取
     *
     * @param filePathAndName 带有完整绝对路径的文件名
     * @return String 返回文本文件的内容
     */
    @JvmStatic
    fun readFileContent(filePathAndName: String): String? {
        try {
            return readFileContent(filePathAndName, null, null, 1024)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }


    /**
     * 读取文本文件内容，以行的形式读取
     *
     * @param filePathAndName 带有完整绝对路径的文件名
     * @param encoding        文本文件打开的编码方式 例如 GBK,UTF-8
     * @param sep             分隔符 例如：#，默认为\n;
     * @param bufLen          设置缓冲区大小
     * @return String 返回文本文件的内容
     */
    @JvmStatic
    fun readFileContent(filePathAndName: String?, encoding: String?, sep: String?, bufLen: Int): String {
        var sep = sep
        if (filePathAndName == null || filePathAndName == "") {
            return ""
        }
        if (sep == null || sep == "") {
            sep = "\n"
        }
        if (!File(filePathAndName).exists()) {
            return ""
        }
        val str = StringBuffer("")
        var fs: FileInputStream? = null
        var isr: InputStreamReader? = null
        var br: BufferedReader? = null
        try {
            fs = FileInputStream(filePathAndName)
            if (encoding == null || encoding.trim { it <= ' ' } == "") {
                isr = InputStreamReader(fs)
            } else {
                isr = InputStreamReader(fs, encoding.trim { it <= ' ' })
            }
            br = BufferedReader(isr, bufLen)

            var data = ""
            while ({ data = br.readLine(); data } != null) {
                str.append(data).append(sep)
            }
        } catch (e: IOException) {
        } finally {
            try {
                br?.close()
                isr?.close()
                fs?.close()
            } catch (e: IOException) {
            }
        }
        return str.toString()
    }


    /**
     * 把Assets里的文件拷贝到sd卡上
     *
     * @param assetManager    AssetManager
     * @param fileName        Asset文件名
     * @param destinationPath 完整目标路径
     * @return 拷贝成功
     */
    @JvmStatic
    fun copyAssetToSDCard(assetManager: AssetManager, fileName: String, destinationPath: String): Boolean {

        try {
            val `is` = assetManager.open(fileName)
            val os = FileOutputStream(destinationPath)

            if (`is` != null && os != null) {
                val data = ByteArray(1024)
                var len: Int = -1
                while ({ len = `is`.read(data); len }() > 0) {
                    os.write(data, 0, len)
                }

                os.close()
                `is`.close()
            }
        } catch (e: IOException) {
            return false
        }

        return true
    }


    /**
     * 调用系统方式打开文件.
     *
     * @param context 上下文
     * @param file    文件
     */
    @JvmStatic
    fun openFile(context: Context, file: File) {

        try {
            // 调用系统程序打开文件.
            val intent = Intent(Intent.ACTION_VIEW)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.setDataAndType(Uri.fromFile(file), MimeTypeMap.getSingleton()
                .getMimeTypeFromExtension(
                    MimeTypeMap
                        .getFileExtensionFromUrl(
                            file.path)))
            context.startActivity(intent)
        } catch (ex: Exception) {
            Toast.makeText(context, "打开失败.", Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * 根据文件路径，检查文件是否不大于指定大小
     *
     * @param filepath 文件路径
     * @param maxSize  最大
     * @return 是否
     */
    @JvmStatic
    fun checkFileSize(filepath: String, maxSize: Int): Boolean {

        val file = File(filepath)
        if (!file.exists() || file.isDirectory) {
            return false
        }
        return file.length() <= maxSize * 1024
    }


    /**
     * @param context 上下文
     * @param file    文件对象
     */
    @JvmStatic
    fun openMedia(context: Context, file: File) {

        if (file.name.endsWith(".png") ||
            file.name.endsWith(".jpg") ||
            file.name.endsWith(".jpeg")) {
            viewPhoto(context, file)
        } else {
            openFile(context, file)
        }
    }


    /**
     * 打开多媒体文件.
     *
     * @param context 上下文
     * @param file    多媒体文件
     */
    @JvmStatic
    fun viewPhoto(context: Context, file: String) {

        viewPhoto(context, File(file))
    }


    /**
     * 打开照片
     *
     * @param context 上下文
     * @param file    文件对象
     */
    @JvmStatic
    fun viewPhoto(context: Context, file: File) {

        try {
            // 调用系统程序打开文件.
            val intent = Intent(Intent.ACTION_VIEW)
            //			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(file), "image/*")
            context.startActivity(intent)
        } catch (ex: Exception) {
            Toast.makeText(context, "打开失败.", Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * 将字符串以charsetName编码保存到文件中
     *
     * @param str         保存的字符串
     * @param fileName    文件的名字
     * @param charsetName 字符串编码
     * @return 是否保存成功
     */
    @JvmOverloads
    @JvmStatic
    fun saveStrToFile(str: String?, fileName: String, charsetName: String? = "UTF-8"): Boolean {

        if (str == null || "" == str) {
            return false
        }

        var stream: FileOutputStream? = null
        try {
            val file = File(fileName)
            if (!file.parentFile!!.exists()) {
                file.parentFile!!.mkdirs()
            }

            var b: ByteArray? = null
            if (charsetName != null && "" != charsetName) {
                b = str.toByteArray(charset(charsetName))
            } else {
                b = str.toByteArray()
            }

            stream = FileOutputStream(file)
            stream.write(b, 0, b.size)
            stream.flush()
            return true
        } catch (e: Exception) {
            return false
        } finally {
            if (stream != null) {
                try {
                    stream.close()
                    stream = null
                } catch (e: Exception) {
                }
            }
        }
    }


    /**
     * 将content://形式的uri转为实际文件路径
     *
     * @param context 上下文
     * @param uri     地址
     * @return uri转为实际文件路径
     */
    @JvmStatic
    fun uriToPath(context: Context, uri: Uri): String? {

        var cursor: Cursor? = null
        try {
            if (uri.scheme!!.equals(URI_TYPE_FILE, ignoreCase = true)) {
                return uri.path
            }
            cursor = context.contentResolver
                .query(uri, null, null, null, null)
            if (cursor!!.moveToFirst()) {
                return cursor.getString(cursor.getColumnIndex(
                    MediaStore.Images.Media.DATA)) //图片文件路径
            }
        } catch (e: Exception) {
            if (null != cursor) {
                cursor.close()
                cursor = null
            }
            return null
        }

        return null
    }


    /**
     * 打开多媒体文件.
     *
     * @param context 上下文
     * @param file    多媒体文件
     */
    @JvmStatic
    fun playSound(context: Context, file: String) {

        playSound(context, File(file))
    }


    /**
     * 打开多媒体文件.
     *
     * @param context 上下文
     * @param file    多媒体文件
     */
    @JvmStatic
    fun playSound(context: Context, file: File) {

        try {
            // 调用系统程序打开文件.
            val intent = Intent(Intent.ACTION_VIEW)
            //			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //			intent.setClassName("com.android.music", "com.android.music.MediaPlaybackActivity");
            intent.setDataAndType(Uri.fromFile(file), "audio/*")
            context.startActivity(intent)
        } catch (ex: Exception) {
            Toast.makeText(context, "打开失败.", Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * 打开视频文件.
     *
     * @param context 上下文
     * @param file    视频文件
     */
    @JvmStatic
    fun playVideo(context: Context, file: String) {

        playVideo(context, File(file))
    }


    /**
     * 打开视频文件.
     *
     * @param context 上下文
     * @param file    视频文件
     */
    @JvmStatic
    fun playVideo(context: Context, file: File) {
        try {
            // 调用系统程序打开文件.
            val intent = Intent(Intent.ACTION_VIEW)
            //			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(file), "video/*")
            context.startActivity(intent)
        } catch (ex: Exception) {
            Toast.makeText(context, "打开失败.", Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * 文件重命名
     *
     * @param oldPath 旧的文件名字
     * @param newPath 新的文件名字
     */
    @JvmStatic
    fun renameFile(oldPath: String, newPath: String) {

        try {
            if (!TextUtils.isEmpty(oldPath) && !TextUtils.isEmpty(newPath)
                && oldPath != newPath) {
                val fileOld = File(oldPath)
                val fileNew = File(newPath)
                fileOld.renameTo(fileNew)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 获取网络请求缓存文件夹
     * @param context 上下文
     * @return 网络请求缓存文件夹
     */
    @JvmStatic
    fun getHttpImageCacheDir(context: Context): File {
        return getCacheDir(context, HTTP_CACHE_DIR_NAME)
    }

    /**
     * 获取缓存文件夹
     *
     * @param context 上下文
     * @param dirName 文件夹名称
     * @return 缓存文件夹
     */
    @JvmStatic
    fun getCacheDir(context: Context, dirName: String): File {
        val rootDir = context.externalCacheDir
        val cacheFile = File(rootDir, dirName)
        if (!cacheFile.exists()) {
            cacheFile.mkdir()
        }
        return cacheFile
    }
}