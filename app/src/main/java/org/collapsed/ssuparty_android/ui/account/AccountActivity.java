package org.collapsed.ssuparty_android.ui.account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.collapsed.ssuparty_android.R;
import org.collapsed.ssuparty_android.ui.main.MainActivity;
import org.collapsed.ssuparty_android.ui.signup.SignupActivity;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AccountActivity extends AppCompatActivity implements AccountContract.View {

    private static final String TAG = MainActivity.class.getSimpleName();


    @BindView(R.id.account_facebook_login_btn)
    LoginButton mLoginButton;

    private AccountPresenter mPresenter;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);

        mPresenter = new AccountPresenter(this);
        mCallbackManager = CallbackManager.Factory.create();
        mPresenter.setFacebookLoginCallback(mLoginButton, mCallbackManager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void redirectSignupActivity() {
        final Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        finish();
    }

}
