package com.example.goadventure.ui.profile.akun.editprofile
import android.net.Uri
import android.view.View
import com.example.goadventure.model.request.UserRequest
import com.example.goadventure.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UpdateProfilePresenter(private val view: UpdateProfileContract.View) : UpdateProfileContract.Presenter{
    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun submitUpdateProfile(userRequest: UserRequest) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.updateProfile(
            userRequest.nama.toString(),
            userRequest.no_telepon.toString(),
            userRequest.alamat.toString()
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if(it.meta?.status.equals("success",true)){
                        it.data?.let { it1 -> view.onUpdateProfileSuccess(it1) }
                    }else{
                        it.meta?.message?.let { it1 -> view.onUpdateProfileFailed(it1) }
                    }
                },
                {
                    view.dismissLoading()
                    view.onUpdateProfileFailed(it?.message.toString())
                }
            )

        mCompositeDisposable!!.add(disposable)
    }


    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }

}