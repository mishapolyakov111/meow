package ru.polyakov.meow.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.polyakov.meow.R
import ru.polyakov.meow.abstractions.HistoryContract
import ru.polyakov.meow.model.entities.CatHistory
import ru.polyakov.meow.presenters.HistoryPresenter

class HistoryFragment : Fragment(), HistoryContract.View {

    private lateinit var presenter: HistoryContract.Presenter
    private lateinit var container: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        container = view.findViewById(R.id.historyContainer)
        presenter = HistoryPresenter()
        presenter.attach(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    override fun onResume() {
        super.onResume()
        presenter.refreshHistory()
    }

    override fun showHistory(cats: List<CatHistory>) {
        container.removeAllViews()
        val inflater = LayoutInflater.from(context)

        cats.forEach { history ->
            val itemView = inflater.inflate(R.layout.item_history, container, false)
            val imageView = itemView.findViewById<ImageView>(R.id.historyImageView)
            val removeButton = itemView.findViewById<Button>(R.id.removeHistoryButton)

            Glide.with(requireContext())
                .load(history.imageUrl)
                .into(imageView)

            removeButton.setOnClickListener {
                presenter.removeHistory(history.id)
            }

            container.addView(itemView)
        }
    }
}