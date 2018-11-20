package paf.songrecorder.helpers

import android.os.Environment
import android.util.Log
import paf.songrecorder.models.Song
import java.io.File
import java.io.IOException


class FileHelper {

    companion object {

        private const val APP_NAME = "Song Recorder"
        const val FOLDER_CREATED = 0
        const val FOLDER_ALREADY_EXISTS = 1
        const val FOLDER_CREATION_FAILED = 2
        private val appFolder = File(Environment.getExternalStorageDirectory()
                .toString() + "/$APP_NAME")

        fun getDirectoryListSortedByLastModified(rootPath: String): ArrayList<File>? {

            val folderList = ArrayList<File>()

            return try {
                val rootFolder = File(rootPath)
                val folders = rootFolder.listFiles()

                for (folder in folders) {
                    if(folder.isDirectory){
                        folderList.add(folder)
                    }
                }
                folderList.sortByDescending { it.lastModified() }
                folderList
            } catch (e: Exception){
                null
            }
        }

        fun getAudioFileListFromDirectorySortedByLastModified(rootPath: String): ArrayList<File>? {
            val fileList = ArrayList<File>()

            return try {
                val rootFolder = File("$rootPath/")
                val files = rootFolder.listFiles()

                for (file in files) {
                    if(!file.isDirectory && file.name.endsWith(".3gp")) {
                        fileList.add(file)
                    }
                }
                fileList.sortByDescending { it.lastModified() }
                fileList
            } catch (e: Exception){
                null
            }

        }

        fun createNewAudioFile(name: String){
            val appFolder = File(Environment.getExternalStorageDirectory().toString() + "/$APP_NAME")
            val file : File
            try {
                if(appFolder.isDirectory) {
                    file = File(appFolder.absolutePath + "/$name")
                    file.mkdirs()
//                File(song.songFolder + DateHelper.getCurrentDateAndTimeAsString())

                } else {
//                appFolder = File(Environment.getExternalStorageDirectory().toString(), APP_NAME)
                    appFolder.mkdirs()
                    file = File(appFolder.absolutePath + "/$name")
                    file.mkdirs()
//                createFolder(newSongFolder.absolutePath + "/$name")
                }
            }catch (e: IOException) {
                Log.e("ERROR CREATING FILE", e.message)
            }
        }

        fun createNewSongFolder(songModel: Song): Int {
            val folder = File(appFolder.absolutePath + "/${songModel.title}")
            try {
                return if(appFolder.isDirectory && !folder.isDirectory) {
                    folder.mkdirs()
                    FOLDER_CREATED
                } else if(appFolder.isDirectory && folder.isDirectory) {
                    FOLDER_ALREADY_EXISTS
                } else {
//                    appFolder = File(Environment.getExternalStorageDirectory().toString(), APP_NAME)
                    appFolder.mkdirs()
                    folder.mkdirs()
                    FOLDER_CREATED
                }
            } catch (eIO: IOException) {
                Log.e("Folder Creation", "Error creating the file $eIO")
                return FOLDER_CREATION_FAILED
            } catch (e: Exception) {
                Log.e("Folder Creation", "$e")
                return FOLDER_CREATION_FAILED
            }
        }
    }
}