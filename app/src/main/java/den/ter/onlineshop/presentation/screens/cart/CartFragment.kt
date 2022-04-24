package den.ter.onlineshop.presentation.screens.cart

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import den.ter.onlineshop.R
import den.ter.onlineshop.databinding.FragmentCartBinding
import den.ter.onlineshop.presentation.screens.start.StartViewModel
import den.ter.onlineshop.presentation.screens.start.adapters.CategoryAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : Fragment() {


    private val viewModel: CartViewModel by viewModel<CartViewModel>()
    lateinit var binding: FragmentCartBinding
    lateinit var rv: RecyclerView
    lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        rv = binding.rv
        adapter = CartAdapter(requireContext())
        rv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv.adapter = adapter
        rv.isNestedScrollingEnabled = false
        binding.backButton.setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        if (isOnline(requireContext())) {
            loadData()
            binding.cardView.visibility = View.VISIBLE
            binding.tvConnectionLost3.visibility = View.GONE
        } else {
            binding.cardView.visibility = View.GONE
            binding.tvConnectionLost3.visibility = View.VISIBLE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadData() {
        viewModel.getCart()
        viewModel.resp.observe(viewLifecycleOwner, Observer {
            adapter.setList(it.basket)
            binding.apply {
                total.text = "$${it.total}"
                delivery.text = it.delivery.toString()
            }
        })
    }

    private fun isOnline(ctx: Context): Boolean {
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork ?: return false
        val activeNetwork = cm.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }


}