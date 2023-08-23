package com.example.goadventure.ui.home
import com.example.goadventure.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter{
    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getHome() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()?.home()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onHomeSuccess(it1) }
                    } else {
                        it.meta?.let { it1 -> view.onHomeFailed(it1.message) }
                    }
                }, {
                    view.dismissLoading()
                    view.onHomeFailed(it.message.toString())
                }
            )
        disposable?.let { mCompositeDisposable?.add(it) }
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }

}