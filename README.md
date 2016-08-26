# IllegalStateExceptionDialogFragment
IllegalStateExceptionDialogFragment  demo 用来测试出现IllegalStateException的情况

下面分析三个分支的情况
feature-test-show-showDialogFragment-error
用来测试，当我们调用 ```dialog.show(getSupportFragmentManager(), TAG_PROGRESS);```  程序会不会抛异常IllegalStateException
启动app后，点击按钮，然后退到后台，可以打开别的app，这个时候一般就能够log到错误（这个测试的是，异步任务结束后，但是我们的应用已经退出了前台，这个时候才开始显示dialog，然后提示报错）

java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
	at android.support.v4.app.FragmentManagerImpl.checkStateLoss(FragmentManager.java:1533)
	at android.support.v4.app.FragmentManagerImpl.enqueueAction(FragmentManager.java:1551)
	at android.support.v4.app.BackStackRecord.commitInternal(BackStackRecord.java:696)
	at android.support.v4.app.BackStackRecord.commit(BackStackRecord.java:662)
	at android.support.v4.app.DialogFragment.show(DialogFragment.java:139)
	at star.github.com.illegalstateexceptiondialogfragment.MainActivity.showProgress(MainActivity.java:37)
	at star.github.com.illegalstateexceptiondialogfragment.MainActivity.access$200(MainActivity.java:13)
	at star.github.com.illegalstateexceptiondialogfragment.MainActivity$ProgressTask.onPostExecute(MainActivity.java:74)
	at star.github.com.illegalstateexceptiondialogfragment.MainActivity$ProgressTask.onPostExecute(MainActivity.java:55)
	at android.os.AsyncTask.finish(AsyncTask.java:636)
	at android.os.AsyncTask.access$600(AsyncTask.java:177)
	at android.os.AsyncTask$InternalHandler.handleMessage(AsyncTask.java:649)
	at android.os.Handler.dispatchMessage(Handler.java:102)
	at android.os.Looper.loop(Looper.java:136)
	at android.app.ActivityThread.main(ActivityThread.java:5135)
	at java.lang.reflect.Method.invokeNative(Native Method)
	at java.lang.reflect.Method.invoke(Method.java:515)
	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:795)
	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:611)
	at dalvik.system.NativeStart.main(Native Method)


对于feature-test-show-dismiss-error分支，测试的是dismiss的时候，程序会不会报错，这个模拟的是在异步任务开始后，应用退出前台了，然后异步任务执行完，取消显示dialog，和这个时候出现了bug

java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
	at android.support.v4.app.FragmentManagerImpl.checkStateLoss(FragmentManager.java:1533)
	at android.support.v4.app.FragmentManagerImpl.enqueueAction(FragmentManager.java:1551)
	at android.support.v4.app.BackStackRecord.commitInternal(BackStackRecord.java:696)
	at android.support.v4.app.BackStackRecord.commit(BackStackRecord.java:662)
	at android.support.v4.app.DialogFragment.dismissInternal(DialogFragment.java:201)
	at android.support.v4.app.DialogFragment.dismiss(DialogFragment.java:167)
	at star.github.com.illegalstateexceptiondialogfragment.MainActivity.hideProgress(MainActivity.java:52)
	at star.github.com.illegalstateexceptiondialogfragment.MainActivity.access$300(MainActivity.java:13)
	at star.github.com.illegalstateexceptiondialogfragment.MainActivity$ProgressTask.onPostExecute(MainActivity.java:74)
	at star.github.com.illegalstateexceptiondialogfragment.MainActivity$ProgressTask.onPostExecute(MainActivity.java:55)
	at android.os.AsyncTask.finish(AsyncTask.java:636)
	at android.os.AsyncTask.access$600(AsyncTask.java:177)
	at android.os.AsyncTask$InternalHandler.handleMessage(AsyncTask.java:649)
	at android.os.Handler.dispatchMessage(Handler.java:102)
	at android.os.Looper.loop(Looper.java:136)
	at android.app.ActivityThread.main(ActivityThread.java:5135)
	at java.lang.reflect.Method.invokeNative(Native Method)
	at java.lang.reflect.Method.invoke(Method.java:515)
	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:795)


对于mater分支的内容，在异步任务过程中，是不会出现问题的..





