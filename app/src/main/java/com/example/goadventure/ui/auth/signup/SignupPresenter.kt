package com.example.goadventure.ui.auth.signup
import android.view.View
import com.example.goadventure.model.request.RegisterRequest
import com.example.goadventure.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SignupPresenter (private val view: SignupContract.View) : SignupContract.Presenter{
    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun submitRegister(registerRequest: RegisterRequest, viewParams: View) {
        view.showLoading()

        val disposable = HttpClient.getInstance().getApi()?.register(
            registerRequest.nama,
            registerRequest.email,
            registerRequest.password,
            registerRequest.password_confirmation,
            registerRequest.alamat,
            registerRequest.no_telepon
        )
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onRegisterSuccess(it1, viewParams) }
                    } else {
                        it.meta?.message?.let { it1 -> view.onRegisterFailed(it1) }
                    }
                }, {
                    view.dismissLoading()
                    view.onRegisterFailed(it.message.toString())
                }
            )
        disposable?.let { mCompositeDisposable?.add(it) }
    }

//    override fun submitPhotoRegister(filePath: Uri, viewParams: View) {
//        view.showLoading()
//
//        var profileImageFile = File(filePath.path)
//        val profileImageRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), profileImageFile)
//        val profileImageParams = MultipartBody.Part.createFormData("file", profileImageFile.name, profileImageRequestBody)
//
//        val disposable = HttpClient.getInstance().getApi()?.registerPhoto(profileImageParams)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.subscribe(
//                {
//                    view.dismissLoading()
//                    if (it.meta?.status.equals("success")) {
//                        it.data?.let { it1 -> view.onRegisterPhotoSuccess(viewParams) }
//                    } else {
//                        view.onRegisterFailed(it.meta?.message.toString())
//                    }
//                }, {
//                    view.dismissLoading()
//                    view.onRegisterFailed(it.message.toString())
//                }
//            )
//        disposable?.let { mCompositeDisposable?.add(it) }
//    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }
}