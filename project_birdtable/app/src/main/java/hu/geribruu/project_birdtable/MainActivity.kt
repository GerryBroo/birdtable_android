package hu.geribruu.project_birdtable

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hu.geribruu.project_birdtable.R
import java.io.File

class MainActivity : AppCompatActivity() {

    companion object OutputFileUri {
        lateinit var outputFileUri : String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        outputFileUri = getOutputDirectory()
    }

    private fun getOutputDirectory(): String {

        val mediaDir = externalMediaDirs?.firstOrNull().let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() } }
        return if (mediaDir.exists())
            mediaDir.toString() else filesDir.toString()
    }


    /** OPTIONS MENU **/
    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }*/
}