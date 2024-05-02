package com.example.kidsnavigation.ui.fragment.social

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kidsnavigation.R
import com.example.kidsnavigation.databinding.FragmentChatBinding
import com.google.firebase.Firebase
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

/**
 * A simple [Fragment] subclass.
 * Use the [ChatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    private lateinit var userName:String
    private lateinit var chatPartner:String

    private var db = Firebase.database
    private lateinit var reference1: DatabaseReference
    private lateinit var reference2: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentChatBinding.inflate(inflater, container, false)

        userName = "parent"
        chatPartner = "child"

        reference1 = db.getReference("messages/${userName}_$chatPartner")
        reference2 = db.getReference("messages/${chatPartner}_$userName")

        binding.messageArea.btnSend.setOnClickListener {
            val message = binding.messageArea.etInputChat.text.toString()
            if(message.isNotBlank()){
                val messageMap = hashMapOf(
                    "message" to message,
                    "user" to userName
                )
                reference1.push().setValue(messageMap)
                reference2.push().setValue(messageMap)
                binding.messageArea.etInputChat.text.clear()
            }
        }

        reference1.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val  map = snapshot.value as HashMap<String, String>
                val  user = map["user"]
                val message = map["message"]

                if ( user == userName)
                addMessage("Du : \n$message", 1)
                else
                    addMessage("$chatPartner : \n$message", 2)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun addMessage(message: String, type:Int){
        try {
            val textView = TextView(requireActivity())
            textView.text = message
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.weight = 1f
            if (type == 1) {
                layoutParams.gravity = Gravity.END
                textView.setBackgroundResource(R.drawable.bubble_out)
            } else {
                layoutParams.gravity = Gravity.START
                textView.setBackgroundResource(R.drawable.bubble_in)
            }
            textView.layoutParams = layoutParams
            binding.chatContainer.addView(textView)
            binding.scrollView.fullScroll(View.FOCUS_DOWN)
        }catch(e : IllegalStateException){
            return
        }
    }


}