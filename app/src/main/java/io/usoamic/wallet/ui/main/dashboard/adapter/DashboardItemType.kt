package io.usoamic.wallet.ui.main.dashboard.adapter

sealed class DashboardItemType {
    object EthBalance : DashboardItemType()
    object UsoBalance : DashboardItemType()
    object Height : DashboardItemType()
    object Supply : DashboardItemType()
}