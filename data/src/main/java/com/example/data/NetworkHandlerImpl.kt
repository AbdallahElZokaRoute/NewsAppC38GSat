package com.example.data

import com.example.domain.repos.NetworkHandler

class NetworkHandlerImpl : NetworkHandler {
    override fun isOnline(): Boolean {
        return true
    }

}