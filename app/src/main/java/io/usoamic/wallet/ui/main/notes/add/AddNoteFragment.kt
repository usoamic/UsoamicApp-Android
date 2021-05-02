package io.usoamic.wallet.ui.main.notes.add

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.usoamickt.enumcls.NoteType
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.custom.edit.SelectEditText
import io.usoamic.wallet.databinding.FragmentAddNoteBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.extensions.clear
import io.usoamic.wallet.extensions.observe
import io.usoamic.wallet.extensions.showMessage
import io.usoamic.wallet.extensions.value
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import javax.inject.Inject

class AddNoteFragment : BaseViewModelFragment(R.layout.fragment_add_note) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<AddNoteViewModel>
    override val viewModel: AddNoteViewModel by viewModels { viewModelFactory }

    override val binding: FragmentAddNoteBinding by viewBinding {
        FragmentAddNoteBinding.bind(it.requireView())
    }

    override fun inject() {
        UsoamicWallet.component.addNoteSubcomponent.create().inject(this)
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