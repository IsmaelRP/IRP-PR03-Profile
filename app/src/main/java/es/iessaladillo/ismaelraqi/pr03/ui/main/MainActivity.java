package es.iessaladillo.ismaelraqi.pr03.ui.main;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import es.iessaladillo.ismaelraqi.irp_pr03_profile.R;
import es.iessaladillo.ismaelraqi.pr03.data.local.Database;
import es.iessaladillo.ismaelraqi.pr03.data.local.model.Avatar;
import es.iessaladillo.ismaelraqi.pr03.utils.KeyboardUtils;
import es.iessaladillo.ismaelraqi.pr03.utils.SnackbarUtils;
import es.iessaladillo.ismaelraqi.pr03.utils.ValidationUtils;

public class MainActivity extends AppCompatActivity {

    private final Database database = Database.getInstance();

    private ImageView imgAvatar;

    private TextView lblAvatar;
    private TextView lblName;
    private TextView lblEmail;
    private TextView lblPhoneNumber;
    private TextView lblAddress;
    private TextView lblWeb;

    private EditText txtName;
    private EditText txtEmail;
    private EditText txtPhoneNumber;
    private EditText txtAddress;
    private EditText txtWeb;

    private ImageView imgEmail;
    private ImageView imgPhonenumber;
    private ImageView imgAddress;
    private ImageView imgWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        // UN ARRAY DE UN ÚNICO ELEMENTO? PARA QÚE UN ARRAY?
        final Avatar[] avatar = new Avatar[1];
        avatar[0] = database.getDefaultAvatar();
        imgAvatar = ActivityCompat.requireViewById(this, R.id.imgAvatar);
        // ESTAS DOS LÍNEAS SE REPITE MÁS ABAJO. CREA UN MÉTODO showAvatar(avatar)
        imgAvatar.setImageResource(database.getDefaultAvatar().getImageResId());
        imgAvatar.setTag(database.getDefaultAvatar().getImageResId());
        imgAvatar.setOnClickListener(v -> {
            avatar[0] = database.getRandomAvatar();
            imgAvatar.setImageResource(avatar[0].getImageResId());
            lblAvatar.setText(avatar[0].getName());
            imgAvatar.setTag(avatar[0].getImageResId());
            lblAvatar.setTag(avatar[0].getName());
        });

        lblAvatar = ActivityCompat.requireViewById(this, R.id.lblAvatar);
        lblAvatar.setText(database.getDefaultAvatar().getName());
        lblAvatar.setOnClickListener(v -> {
            // ESTAS LÍNEAS SON IGUAL QUE LAS DEL CLICK LISTENER ANTERIOR. HAZ UN MÉTODO
            // changeAvatar()
            avatar[0] = database.getRandomAvatar();
            imgAvatar.setImageResource(avatar[0].getImageResId());
            lblAvatar.setText(avatar[0].getName());
            imgAvatar.setTag(avatar[0].getImageResId());
            lblAvatar.setTag(avatar[0].getName());
        });

        lblName = ActivityCompat.requireViewById(this, R.id.lblName);
        txtName = ActivityCompat.requireViewById(this, R.id.txtName);
        txtName.setOnFocusChangeListener((v, hasFocus) -> txtSwapBold(lblName));
        txtName.addTextChangedListener(new GenericTextWatcher(txtName));

        lblEmail = ActivityCompat.requireViewById(this, R.id.lblEmail);
        txtEmail = ActivityCompat.requireViewById(this, R.id.txtEmail);
        txtEmail.setOnFocusChangeListener((v, hasFocus) -> txtSwapBold(lblEmail));
        txtEmail.addTextChangedListener(new GenericTextWatcher(txtEmail));

        lblPhoneNumber = ActivityCompat.requireViewById(this, R.id.lblPhonenumber);
        txtPhoneNumber = ActivityCompat.requireViewById(this, R.id.txtPhonenumber);
        txtPhoneNumber.setOnFocusChangeListener((v, hasFocus) -> txtSwapBold(lblPhoneNumber));
        txtPhoneNumber.addTextChangedListener(new GenericTextWatcher(txtPhoneNumber));

        lblAddress = ActivityCompat.requireViewById(this, R.id.lblAddress);
        txtAddress = ActivityCompat.requireViewById(this, R.id.txtAddress);
        txtAddress.setOnFocusChangeListener((v, hasFocus) -> txtSwapBold(lblAddress));
        txtAddress.addTextChangedListener(new GenericTextWatcher(txtAddress));

        lblWeb = ActivityCompat.requireViewById(this, R.id.lblWeb);
        txtWeb = ActivityCompat.requireViewById(this, R.id.txtWeb);
        txtWeb.setOnFocusChangeListener((v, hasFocus) -> txtSwapBold(lblWeb));
        txtWeb.addTextChangedListener(new GenericTextWatcher(txtWeb));

        txtWeb.setOnEditorActionListener((v, actionId, event) -> {
            save();
            return true;
        });

        imgEmail = ActivityCompat.requireViewById(this, R.id.imgEmail);
        imgPhonenumber = ActivityCompat.requireViewById(this, R.id.imgPhonenumber);
        imgAddress = ActivityCompat.requireViewById(this, R.id.imgAddress);
        imgWeb = ActivityCompat.requireViewById(this, R.id.imgWeb);
    }

    // ESTE MÉTODO ME HA GUSTADO
    private void txtSwapBold(TextView txt) {
        if (txt.getTypeface().isBold()) {
            txt.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        } else {
            txt.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
    }

    private class GenericTextWatcher implements TextWatcher {

        private final View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.txtName:
                    isWrongName();
                    break;
                case R.id.txtEmail:
                    isWrongEmail();
                    break;
                case R.id.txtPhonenumber:
                    isWrongPhonenumber();
                    break;
                case R.id.txtAddress:
                    isWrongAddress();
                    break;
                case R.id.txtWeb:
                    isWrongWeb();
                    break;
            }
        }
    }

    // DO NOT TOUCH
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // DO NOT TOUCH
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuSave) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // TODOS ESTOS MÉTODOS isWrong... HACEN PRÁCTICAMENTE LO MISMO. PODRÍAS CREAR
    // UN MÉTODO QUE RECIBIERA ENTRE OTRAS COSAS UN BOOLEANO INDICATIVO DE SI
    // EL CAMPO ES VÁLIDO O NO Y REALIZARA LOS CAMBIOS VISUALES CORRESPONDIENTES.
    private boolean isWrongName() {
        boolean isWrong;
        if (txtName.getText().toString().length() <= 0) {
            txtName.setError((getString(R.string.main_invalid_data)));
            // SE TE PROPORCIONA color_state_selector PARA QUE NO TENGAS QUE HACER ESTO.
            lblName.setTextColor(getResources().getColor(R.color.colorError));
            lblName.setEnabled(false);
            isWrong = true;
        } else {
            txtName.setError(null);
            lblName.setTextColor(getResources().getColor(R.color.colorBlack));
            lblName.setEnabled(true);
            isWrong = false;
        }
        return isWrong;
    }

    private boolean isWrongEmail() {
        boolean isWrong;
        if (!ValidationUtils.isValidEmail(txtEmail.getText().toString())) {
            txtEmail.setError((getString(R.string.main_invalid_data)));
            imgEmail.setEnabled(false);
            lblEmail.setTextColor(getResources().getColor(R.color.colorError));
            lblEmail.setEnabled(false);
            isWrong = true;
        } else {
            txtAddress.setError(null);
            imgEmail.setEnabled(true);
            lblEmail.setTextColor(getResources().getColor(R.color.colorBlack));
            lblEmail.setEnabled(true);
            isWrong = false;
        }
        return isWrong;
    }

    private boolean isWrongPhonenumber() {
        boolean isWrong;
        if (!ValidationUtils.isValidPhone(txtPhoneNumber.getText().toString())) {
            txtPhoneNumber.setError((getString(R.string.main_invalid_data)));
            imgPhonenumber.setEnabled(false);
            lblPhoneNumber.setTextColor(getResources().getColor(R.color.colorError));
            lblPhoneNumber.setEnabled(false);
            isWrong = true;
        } else {
            txtPhoneNumber.setError(null);
            imgPhonenumber.setEnabled(true);
            lblPhoneNumber.setTextColor(getResources().getColor(R.color.colorBlack));
            lblPhoneNumber.setEnabled(true);
            isWrong = false;
        }
        return isWrong;
    }

    private boolean isWrongAddress() {
        boolean isWrong;
        if (txtAddress.getText().toString().length() <= 0) {
            txtAddress.setError((getString(R.string.main_invalid_data)));
            imgAddress.setEnabled(false);
            lblAddress.setTextColor(getResources().getColor(R.color.colorError));
            lblAddress.setEnabled(false);
            isWrong = true;
        } else {
            txtAddress.setError(null);
            imgAddress.setEnabled(true);
            lblAddress.setTextColor(getResources().getColor(R.color.colorBlack));
            lblAddress.setEnabled(true);
            isWrong = false;
        }
        return isWrong;
    }

    private boolean isWrongWeb() {
        boolean isWrong;
        if (!ValidationUtils.isValidUrl(txtWeb.getText().toString())) {
            txtWeb.setError((getString(R.string.main_invalid_data)));
            imgWeb.setEnabled(false);
            lblWeb.setTextColor(getResources().getColor(R.color.colorError));
            lblWeb.setEnabled(false);
            isWrong = true;
        } else {
            txtWeb.setError(null);
            imgWeb.setEnabled(true);
            lblWeb.setTextColor(getResources().getColor(R.color.colorBlack));
            lblWeb.setEnabled(true);
            isWrong = false;
        }
        return isWrong;
    }

    private void save() {
        boolean flag = true;
        KeyboardUtils.hideSoftKeyboard(this);

        // HAZ MEJOR UN MÉTODO isFormValid()
        if (isWrongName()) {
            flag = false;
        }
        if (isWrongEmail()) {
            flag = false;
        }
        if (isWrongPhonenumber()) {
            flag = false;
        }
        if (isWrongAddress()) {
            flag = false;
        }

        if (isWrongWeb()) {
            flag = false;
        }

        if (flag) {
            SnackbarUtils.snackbar(imgAvatar, getString(R.string.main_saved_succesfully), Snackbar.LENGTH_SHORT);
        } else {
            SnackbarUtils.snackbar(imgAvatar, getString(R.string.main_error_saving), Snackbar.LENGTH_SHORT);
        }
    }

}
