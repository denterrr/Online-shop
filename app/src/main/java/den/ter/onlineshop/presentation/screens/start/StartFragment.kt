package den.ter.onlineshop.presentation.screens.start

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import den.ter.onlineshop.R
import den.ter.onlineshop.databinding.FragmentStartBinding
import den.ter.onlineshop.domain.models.besthotmodel.BestSeller
import den.ter.onlineshop.presentation.screens.start.adapters.BestAdapter
import den.ter.onlineshop.presentation.screens.start.adapters.CategoryAdapter
import den.ter.onlineshop.presentation.screens.start.adapters.HotAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : Fragment() {

    private val viewModel: StartViewModel by viewModel<StartViewModel>()
    lateinit var binding: FragmentStartBinding
    lateinit var rv_category: RecyclerView
    lateinit var adapter_category: CategoryAdapter
    lateinit var rv_hot: RecyclerView
    lateinit var adapter_hot: HotAdapter
    lateinit var rv_best: RecyclerView
    lateinit var adapter_best: BestAdapter
    lateinit var spinner: Spinner
    lateinit var spinnerBrand: Spinner
    lateinit var spinnerPrice: Spinner
    lateinit var spinnerSize: Spinner
    var filterIsVisible = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        spinner = binding.spinner
        spinnerBrand = binding.spinnerBrand
        spinnerPrice = binding.spinnerPrice
        spinnerSize = binding.spinnerSize
        val spinnerAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.cities,
            R.layout.spinner_item)
        val spinnerBrandAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.brands,
            R.layout.spinner_item)
        val spinnerPriceAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.prices,
            R.layout.spinner_item)
        val spinnerSizeAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.sizes,
            R.layout.spinner_item)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerBrandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPriceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
        spinnerBrand.adapter = spinnerBrandAdapter
        spinnerPrice.adapter = spinnerPriceAdapter
        spinnerSize.adapter = spinnerSizeAdapter
        rv_best = binding.rvBest
        adapter_best = BestAdapter(requireContext(), findNavController())
        rv_best.adapter = adapter_best
        rv_category = binding.rvCategory
        adapter_category = CategoryAdapter()
        rv_category.adapter = adapter_category
        rv_hot = binding.rvHot
        adapter_hot = HotAdapter(requireContext())
        rv_hot.adapter = adapter_hot
        rv_hot.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,
            false)
        rv_category.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,
                false)
        rv_best.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv_best.isNestedScrollingEnabled = false
        rv_hot.isNestedScrollingEnabled = false
        rv_category.isNestedScrollingEnabled = false

        val nav = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        nav?.setOnItemReselectedListener {
            if (it.itemId == R.id.startFragment) {
                binding.mainScroll.smoothScrollTo(0, 0)
            }

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
            binding.tvConnectionLost.visibility = View.GONE
        } else binding.tvConnectionLost.visibility = View.VISIBLE

        binding.filter.setOnClickListener {
            val nav1 = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_view)
            nav1?.visibility = View.GONE
            binding.apply {
                rvBest.visibility = View.GONE
                tvBest.visibility = View.GONE
                tvSeeMore2.visibility = View.GONE
                filterView.visibility = View.VISIBLE
            }
        }

        binding.closeButton.setOnClickListener {
            val nav1 = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_view)
            nav1?.visibility = View.VISIBLE
            binding.apply {
                rvBest.visibility = View.VISIBLE
                tvBest.visibility = View.VISIBLE
                tvSeeMore2.visibility = View.VISIBLE
                filterView.visibility = View.GONE
            }
        }
        binding.doneButton.setOnClickListener {
            if (isOnline(requireContext())) filter()
        }
    }

    private fun loadData() {
        viewModel.getBestsAndHots()
        viewModel.resp.observe(viewLifecycleOwner, Observer {
            adapter_best.setList(it.best_seller)
            adapter_hot.setList(it.home_store)
        })
        adapter_category.update()

        viewModel.getCart()
        viewModel.respCart.observe(viewLifecycleOwner, Observer {
            val nav1 = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_view)
            val badge = nav1?.getOrCreateBadge(R.id.cartFragment)
            badge?.isVisible = true
            badge?.number = it.basket.size

        })
    }

    private fun filter() {
        val nav1 = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        nav1?.visibility = View.VISIBLE
        binding.apply {
            rvBest.visibility = View.VISIBLE
            tvBest.visibility = View.VISIBLE
            tvSeeMore2.visibility = View.VISIBLE
            filterView.visibility = View.GONE
        }
        val brand = spinnerBrand.selectedItem.toString()
        val price = spinnerPrice.selectedItem.toString()
        val ind = price.indexOf("–")
        val minPrice = price.substring(1, ind - 1)
        val maxPrice = price.substring(ind + 3, price.length)
        filterBest(brand, minPrice, maxPrice, viewModel)
    }

    private fun filterBest(
        brand: String,
        minPrice: String,
        maxPrice: String,
        viewModel: StartViewModel,
    ) {
        var name = brand
        if (brand == "All") name = "a"
        viewModel.resp.observe(viewLifecycleOwner, Observer {
            val listFiltered = it.best_seller.filter {
                (it.title.startsWith(name, true) || it.title.contains(name, true)) &&
                        it.price_without_discount >= minPrice.toInt() &&
                        it.price_without_discount <= maxPrice.toInt()
            }
            adapter_best.setList(listFiltered)
            if (listFiltered.isNotEmpty()) {
                binding.nosuch.visibility = View.GONE
            } else {
                binding.nosuch.visibility = View.VISIBLE
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