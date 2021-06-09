package io.usoamic.wallet.ui.main.notes.viewnotes

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItem
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.custom.InputDialogBuilder
import io.usoamic.wallet.databinding.FragmentHistoryBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.domain.models.AppArguments
import io.usoamic.wallet.extensions.navigate
import io.usoamic.wallet.extensions.observe
import io.usoamic.wallet.models.notes.ShowNoteInfo
import io.usoamic.wallet.ui.base.BaseSrViewModelFragment
import io.usoamic.wallet.ui.main.notes.viewnotes.adapter.NotesAdapter
import javax.inject.Inject

class NotesFragment : BaseSrViewModelFragment(R.layout.fragment_notes) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<NotesViewModel>
    override val viewModel: NotesViewModel by viewModels { viewModelFactory }
    override val srLayout: SwipeRefreshLayout
        get() = binding.srLayout

    private val notesAdapter = NotesAdapter {
        showNote(it)
    }

    override val binding: FragmentHistoryBinding by viewBinding {
        FragmentHistoryBinding.bind(it.requireView())
    }

    private lateinit var findDialog: AlertDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSupportActionBar(binding.toolbar, false)
        setHasOptionsMenu(true)

        initRecyclerView()
    }

    override fun inject() {
        UsoamicWallet.component.notesSubcomponent.create().inject(this)
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.ldData, ::setData)
        observe(viewModel.leShowNote, ::showNote)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.notes_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addNote -> {
                navigator.navigate(R.id.addNoteFragment)
            }
            R.id.findNote -> {
                showFindNoteDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showFindNoteDialog() {
        findDialog = InputDialogBuilder(requireContext())
            .setTitle(R.string.app_name)
            .setHint(R.string.find_dialog_note_ref_id)
            .setInputType(InputType.TYPE_CLASS_NUMBER)
            .setPositiveButton(R.string.find_note, viewModel::onFindNoteClick)
            .create()

        findDialog.show()
    }

    private fun setData(list: List<NoteItem>) = with(binding) {
        viewNoData.isVisible = list.isEmpty()
        notesAdapter.addAll(list)
    }

    private fun initRecyclerView() = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = notesAdapter
    }

    private fun showNote(data: ShowNoteInfo) {
        val args = AppArguments.Note(
            id = data.id,
            noteType = data.noteType
        )
        navigator.navigate(
            navResId = R.id.noteFragment,
            args = args
        )
    }

    override fun showProgress(isProgress: Boolean) = with(binding) {
        pbContainer.progressBar.isVisible = isProgress
        srLayout.isInvisible = isProgress
    }
}