package pl.pijok.remotecontrolapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pl.pijok.remotecontrolapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private var mainHandler = Handler(Looper.getMainLooper())

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainHandler.post(object : Runnable {
            @SuppressLint("SetTextI18n")
            override fun run() {
                var connected = PacketSender.checkConnection()
                if (connected) {
                    binding.status.text = "Status: Connected"
                }
                else{
                    binding.status.text = "Status: Disconnected"
                }
                mainHandler.postDelayed(this, 1000)
            }
        })

        binding.leftMouseButton.setOnClickListener {
            PacketSender.handleLeftMoseButton()
        }

        binding.rightMouseButton.setOnClickListener {
            PacketSender.handleRightMoseButton()
        }

        binding.connect.setOnClickListener {
            PacketSender.setIp(binding.serverIp.text.toString())
        }

        binding.space.setOnClickListener {
            PacketSender.handleSpace()
        }

        binding.enter.setOnClickListener {
            PacketSender.handleEnter()
        }

        binding.upArrow.setOnClickListener {
            PacketSender.handleUpArrow()
        }

        binding.downArrow.setOnClickListener {
            PacketSender.handleDownArrow()
        }

        binding.leftArrow.setOnClickListener {
            PacketSender.handleLeftArrow()
        }

        binding.rightArrow.setOnClickListener {
            PacketSender.handleRightArrow()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}