package io.usoamic.app.ui.main.notes.add

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.app.R
import io.usoamic.app.UsoamicApp
import io.usoamic.app.custom.edit.SelectEditText
import io.usoamic.app.databinding.FragmentAddNoteBinding
import io.usoamic.app.di.other.ViewModelFactory
import io.usoamic.app.extensions.clear
import io.usoamic.app.extensions.observe
import io.usoamic.app.extensions.showMessage
import io.usoamic.app.extensions.value
import io.usoamic.app.ui.base.BaseViewModelFragment
import io.usoamic.usoamickt.enumcls.NoteType
import javax.inject.Inject
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

class AddNoteFragment : BaseViewModelFragment(R.layout.fragment_add_note) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<AddNoteViewModel>
    override val viewModel: AddNoteViewModel by viewModels { viewModelFactory }

    override val binding: FragmentAddNoteBinding by viewBinding {
        FragmentAddNoteBinding.bind(it.requireView())
    }

    override fun inject() {
        UsoamicApp.component.addNoteSubcomponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initNoteTypeEditText()
    }

    private fun initNoteTypeEditText() = with(binding) {
        etType.items = listOf(
            SelectEditText.CellData(
                name = getString(R.string.note_type_public),
                value = NoteType.PUBLIC
            ),
            SelectEditText.CellData(
                name = getString(R.string.note_type_unlisted),
                value = NoteType.UNLISTED
            )
        )
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.leNoteAdded, ::onNoteAdded)
    }

    override fun initListeners() = with(binding) {
        btnAdd.setOnClickListener {
            viewModel.onAddClick(
                password = etPassword.value,
                content = etContent.value,
                noteType = etType.value,
                txSpeed = etTxSpeed.value
            )
        }
    }

    override fun showProgress(isProgress: Boolean) = with(binding) {
        pbContainer.progressBar.isVisible = isProgress
        clContainer.isInvisible = isProgress
    }

    private fun onNoteAdded(message: String) = with(binding) {
        etPassword.clear()
        showMessage(message)
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
    }
}