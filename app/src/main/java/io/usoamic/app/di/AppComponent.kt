package io.usoamic.app.di

import dagger.Component
import io.usoamic.commons.crossplatform.scopes.AppScope
import io.usoamic.app.di.modules.CompatModule
import io.usoamic.app.ui.single.SingleActivity
import io.usoamic.app.di.modules.RepositoryModule
import io.usoamic.app.di.modules.UsoamicModule
import io.usoamic.app.di.subcomponents.add.AddSubcomponent
import io.usoamic.app.di.subcomponents.auth.AuthSubcomponent
import io.usoamic.app.di.subcomponents.unlock.UnlockSubcomponent
import io.usoamic.app.di.subcomponents.create.CreateSubcomponent
import io.usoamic.app.di.subcomponents.dashboard.DashboardSubcomponent
import io.usoamic.app.di.subcomponents.deposit.DepositSubcomponent
import io.usoamic.app.di.subcomponents.history.HistorySubcomponent
import io.usoamic.app.di.subcomponents.notes.add.AddNoteSubcomponent
import io.usoamic.app.di.subcomponents.notes.viewnote.NoteSubcomponent
import io.usoamic.app.di.subcomponents.notes.viewnotes.NotesSubcomponent
import io.usoamic.app.di.subcomponents.withdraw.WithdrawSubcomponent
import io.usoamic.app.ui.start.StartFragment
import javax.inject.Singleton

@Singleton
@AppScope
@Component(modules = [UsoamicModule::class, RepositoryModule::class, RepositoryModule::class, CompatModule::class])
interface AppComponent {
    val authSubcomponent: AuthSubcomponent
    val addSubcomponent: AddSubcomponent.Factory
    val unlockSubcomponent: UnlockSubcomponent.Factory

    val createSubcomponent: CreateSubcomponent.Factory
    val dashboardSubcomponent: DashboardSubcomponent.Factory
    val depositSubcomponent: DepositSubcomponent.Factory
    val withdrawSubcomponent: WithdrawSubcomponent.Factory
    val historySubcomponent: HistorySubcomponent.Factory
    val noteSubcomponent: NoteSubcomponent.Factory
    val notesSubcomponent: NotesSubcomponent.Factory
    val addNoteSubcomponent: AddNoteSubcomponent.Factory

    fun inject(activity: SingleActivity)
    fun inject(activity: StartFragment)
}