package paf.songrecorder

import org.junit.Assert.assertEquals
import org.junit.Test
import paf.songrecorder.helpers.FileHelper
import paf.songrecorder.models.Song
import paf.songrecorder.viewmodels.SongModel

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FileHelperTest {

    @Test
    fun addition_isCorrect() {
        val expectedResultCode = FileHelper.FOLDER_CREATED
        val resultCodeTest = FileHelper.createNewSongFolder(
                SongModel(Song("FileHelperTest", null, ""))
        )

        assertEquals(expectedResultCode, resultCodeTest)
    }
}
