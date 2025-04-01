package ru.polyakov.meow.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.polyakov.meow.R
import ru.polyakov.meow.abstractions.MainContract
import ru.polyakov.meow.presenters.MainPresenter

class MainFragment : Fragment(), MainContract.View {
    private lateinit var presenter: MainPresenter
    private lateinit var imageView: ImageView
    private lateinit var codeInput: EditText
    private lateinit var loadButton: Button
    private lateinit var likeButton: Button
    private lateinit var progressBar: ProgressBar
    private var currentStatusCode: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация Views
        imageView = view.findViewById(R.id.catImageView)
        codeInput = view.findViewById(R.id.codeInput)
        loadButton = view.findViewById(R.id.loadButton)
        likeButton = view.findViewById(R.id.likeButton)
        progressBar = view.findViewById(R.id.progressBar)

        // Инициализация презентера
        presenter = MainPresenter()
        presenter.attach(this)

        // Настройка кнопо1
        loadButton.setOnClickListener {
            presenter.loadCatByCode(codeInput.text.toString())
        }

        likeButton.setOnClickListener {
            presenter.likeCat(currentStatusCode)
            Toast.makeText(context, "Котик добавлен в избранное", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    override fun showCat(statusCode: Int, imageUrl: String) {
        currentStatusCode = statusCode
        likeButton.visibility = View.VISIBLE
        context?.let {
            Glide.with(it)
                .load(imageUrl)
                .into(imageView)
        }
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        likeButton.visibility = View.GONE
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        imageView.visibility = View.GONE
        likeButton.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        imageView.visibility = View.VISIBLE
    }
}