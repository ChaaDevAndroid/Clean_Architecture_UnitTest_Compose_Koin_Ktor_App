package com.chaaba.cleanarchitecturecomposekoinktorunittestapp

import android.app.Application
import com.chaaba.core.CoreDependencies
import com.chaaba.gateways.FavoritesGatewayImplementer
import com.chaaba.gateways.GatewaysDependencies
import com.chaaba.gateways.MoviesGatewayImplementer


class MovieApp : Application(){

    override fun onCreate() {
        super.onCreate()

        GatewaysDependencies.with(this)
        CoreDependencies.moviesGateway(MoviesGatewayImplementer())
        CoreDependencies.favoritesGateway(FavoritesGatewayImplementer())
    }
}