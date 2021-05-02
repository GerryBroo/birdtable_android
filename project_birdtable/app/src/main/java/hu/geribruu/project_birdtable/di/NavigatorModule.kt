package hu.geribruu.project_birdtable.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import hu.geribruu.project_birdtable.navigator.AppNavigator
import hu.geribruu.project_birdtable.navigator.AppNavigatorImpl

@InstallIn(ActivityComponent::class)
@Module
abstract class NavigatorModule {

    @Binds
    abstract fun bindNavigator(impl: AppNavigatorImpl): AppNavigator
}