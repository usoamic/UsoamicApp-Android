package io.usoamic.app.ui.main.notes.viewnote

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItem
import io.usoamic.app.BuildConfig
import io.usoamic.app.R
import io.usoamic.app.UsoamicApp
import io.usoamic.app.databinding.FragmentNoteBinding
import io.usoamic.app.di.other.ViewModelFactory
import io.usoamic.app.domain.models.AppArguments
import io.usoamic.app.extensions.ARGS
import io.usoamic.app.extensions.observe
import io.usoamic.app.extensions.requireParcelable
import io.usoamic.app.extensions.toLocalDateTime
import io.usoamic.app.ui.base.BaseSrViewModelFragment
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class NoteFragment : BaseSrViewModelFragment(R.layout.fragment_note) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<NoteViewModel>
    override val viewModel: NoteViewModel by viewModels { viewModelFactory }

    override val srLayout: SwipeRefreshLayout
        get() = binding.srLayout

    override val binding: FragmentNoteBinding by viewBinding {
        FragmentNoteBinding.bind(it.requireView())
    }

    private val args: AppArguments.Note get() = requireArguments().requireParcelable(ARGS)
    private val dateFormatter = DateTimeFormatter.ofPattern(BuildConfig.DATE_FORMAT)

    override fun inject() {
        UsoamicApp.component.noteSubcomponent.create(args).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
    }

    private fun setToolbar() = with(binding) {
        setSupportActionBar(toolbar, true)
        toolbar.title = getString(
            R.string.title_note_fragment_with_id,
            args.id
        )
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.ldData, ::setData)
    }

    override fun showProgress(isProgress: Boolean) = with(binding) {
        pbContainer.progressBar.isVisible = isProgress
        srLayout.isInvisible = isProgress
    }

    private fun setData(item: NoteItem) = with(binding) {
        cvContainer.isVisible = true

        val author = item.author
        val date = item.timestamp.toLocalDateTime()

        tvType.text = getString(
            when (item) {
                is NoteItem.Public -> R.string.note_type_public
                is NoteItem.Unlisted -> R.string.note_type_unlisted
            }
        )

        tvDate.text = dateFormatter.format(date)

        tvAuthor.text = getString(
            if (item.isAuthor) {
                R.string.note_author_is_you
            } else {
                R.string.note_author
            },
            author
        )

        tvContent.text = item.content
    }
}