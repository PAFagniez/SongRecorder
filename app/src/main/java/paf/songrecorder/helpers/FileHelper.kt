package paf.songrecorder.helpers

import java.io.File


class FileHelper {

    companion object {
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

        fun getFileListFromDirectorySortedByLastModified(rootPath: String): ArrayList<File>? {
            val fileList = ArrayList<File>()

            return try {
                val rootFolder = File(rootPath)
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
    }
}