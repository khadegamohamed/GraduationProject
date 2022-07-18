package com.example.graduationproject.presentation.home.view



import com.example.graduationproject.R.menu
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject.databinding.FragmentHomeBinding
import com.example.graduationproject.presentation.home.model.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.graduationproject.domain.common.SharedPreferenceManager


class HomeFragment : Fragment() ,PostsListener{
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var sharedPrafrence:SharedPreferenceManager
    private lateinit var arrayofposts:ArrayList<PostsPojo>
    private lateinit var  adepter: PostsAdepter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = HomeViewModel(requireContext())
        sharedPrafrence = SharedPreferenceManager(requireContext())
        val lm:RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerofposts.layoutManager = lm
        binding.recyclerofposts.setHasFixedSize(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         arrayofposts = ArrayList<PostsPojo>()
        homeViewModel.getAllPosts()
        homeViewModel.mutablePostsResponse.observe(viewLifecycleOwner,{
            if(it.isSuccessful){
                arrayofposts = it.body() as ArrayList<PostsPojo>
                Log.d("Successful", it.isSuccessful.toString())
                if(arrayofposts!=null){
                    adepter= PostsAdepter(arrayofposts,requireContext(),this,requireView())
                    adepter.notifyDataSetChanged()
                    binding.recyclerofposts.adapter = adepter
                    binding.progressBar.visibility=View.GONE
                }else{
                    Log.d("Successful", "null")
                }

            }else{
                Log.d("Errorinhome", it.message().toString())
            }
        })

       homeViewModel.getProfileInfo()

        homeViewModel.profileResponse.observe(viewLifecycleOwner,
            { response ->
            if (response.isSuccessful) {
                val result = response.body()
                if (result != null) {
                   sharedPrafrence.saveProfileName(result.profile_name)
                   sharedPrafrence.saveProfilePhoto(result.profile_photo)
                    Log.d("Successful", "onViewCreated: succeeded saveing Profile information")
                }
            } else {
                Log.d("Successful", "onViewCreated:Failed saveing Profile information")
            }
        })


        binding.WritePost.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragment2ToNewPostFragment()
            )
        }
        binding.swip.setOnRefreshListener {
            homeViewModel.getAllPosts()
            homeViewModel.mutablePostsResponse.observe(viewLifecycleOwner,{
                if(it.isSuccessful){
                    arrayofposts = it.body() as ArrayList<PostsPojo>
                    Log.d("Successful", it.isSuccessful.toString())
                    if(arrayofposts!=null){
                        adepter= PostsAdepter(arrayofposts,requireContext(),this,requireView())
                        adepter.notifyDataSetChanged()
                        binding.recyclerofposts.adapter = adepter
                        binding.progressBar.visibility=View.GONE
                    }else{
                        Log.d("Successful", "null")
                    }

                }else{
                    Log.d("Errorinhome", it.message().toString())
                }
            })
            binding.swip.isRefreshing = false
        }

    }


    override fun onClickLike(id:String,postion:Int) {

        homeViewModel.addLike(id)
        homeViewModel.mutableAddLikeResponse.observe(viewLifecycleOwner,{
            if(it.isSuccessful){
                arrayofposts[postion].likesNum =it.body()!!.likesNum
                adepter.notifyItemChanged(postion)
                Toast.makeText(requireContext(),"you add like",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"you cannot add like",Toast.LENGTH_SHORT).show()
            }
        })


    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClickDeletOrEdit(context: Context,view: View,idPost:String) {
         var menu: PopupMenu? = null
        menu = PopupMenu(context, view)
        menu.setGravity(Gravity.TOP)
        menu.getMenu().add("Edit").setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToEditPostFragment(idPost))
                return false
            }

        })
        menu.getMenu().add("Delete").setOnMenuItemClickListener(object :MenuItem.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                homeViewModel.deletePost(idPost)
                homeViewModel.mutableDeleteResponse.observe(viewLifecycleOwner,{
                    if (it.isSuccessful){
                        Toast.makeText(requireContext(),it.body(), Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(requireContext(),it.body(), Toast.LENGTH_LONG).show()
                    }
                }
                )
                return false
            }
        })
       menu.show()

    }


    override fun onClickComment(comments:List<Comment>,id:String) {
        var objectOfComments = ListOfComments(comments,id)
  findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToCommentsFragment(objectOfComments))
    }


    override fun onClickImages(image:String) {
      findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToListOfPostImageFragment(image))
    }

    override fun onClickNumberOfLikes(likes:List<Like>) {
       var objectOfLikes :ListOfLikes = ListOfLikes(likes)
        findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToWhoIsLike(objectOfLikes))
    }


}


