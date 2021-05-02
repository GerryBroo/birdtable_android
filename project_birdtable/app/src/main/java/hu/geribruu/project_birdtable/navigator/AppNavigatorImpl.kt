package hu.geribruu.project_birdtable.navigator

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import hu.geribruu.project_birdtable.ui.detail.BirdDetailActivity
import hu.geribruu.project_birdtable.ui.detail.BirdDetailViewModel
import hu.geribruu.project_birdtable.ui.detail.BirdDetailViewModel.Companion.BIRD_ID
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor(private val activity: FragmentActivity) : AppNavigator {

    override fun navigateToBirdDetail(birdId: Long) {
        val intent = Intent(activity, BirdDetailActivity::class.java)
        intent.putExtra(BIRD_ID, birdId)
        activity.startActivity(intent)
    }
}