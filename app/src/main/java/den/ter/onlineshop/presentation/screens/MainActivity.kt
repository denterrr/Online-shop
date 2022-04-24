package den.ter.onlineshop.presentation.screens

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import den.ter.onlineshop.R
import den.ter.onlineshop.databinding.ActivityMainBinding
import den.ter.onlineshop.presentation.screens.cart.CartFragment
import den.ter.onlineshop.presentation.screens.favorite.FavoriteFragment
import den.ter.onlineshop.presentation.screens.profile.ProfileFragment
import den.ter.onlineshop.presentation.screens.start.StartFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_fragment)
        binding.bottomNavView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, dest, _ ->
            if (dest.id == R.id.productDetailFragment || dest.id == R.id.cartFragment) {
                binding.bottomNavView.visibility = View.GONE
            } else {
                binding.bottomNavView.visibility = View.VISIBLE
            }

        }

    }


}