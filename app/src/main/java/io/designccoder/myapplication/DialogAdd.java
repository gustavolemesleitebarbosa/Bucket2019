package io.designccoder.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import beans.Drop;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class DialogAdd extends DialogFragment {

    private ImageButton mbtnClose;
    private EditText   mInputWhat;
    private DatePicker mInputWhen;
    private Button mBtnAdd;

    private View.OnClickListener mBtnClickListenner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id){
                case R.id.btn_add_it:
                addAction();
                break;


            }
            dismiss();
        }
    };

    //TODO process date
    private void addAction() {
        String what=mInputWhat.getText().toString();
        long now =System.currentTimeMillis();
        Realm.init(getActivity());
        RealmConfiguration configuration= new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
        Realm realm = Realm.getDefaultInstance();
        Drop drop = new Drop(what, now, 0, false);
        realm.beginTransaction();
        realm.copyToRealm(drop);
        realm.commitTransaction();
        realm.close();
    }


    public DialogAdd() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mbtnClose = view.findViewById(R.id.btn_close);
        mInputWhat = (EditText)view.findViewById(R.id.et_drop);
        mInputWhen = view.findViewById(R.id.bpv_date);
        mBtnAdd = view.findViewById(R.id.btn_add_it);
        mBtnAdd.setOnClickListener(mBtnClickListenner );
        mbtnClose.setOnClickListener(mBtnClickListenner );


    }
}
