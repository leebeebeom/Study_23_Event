package com.beebeom.a23_event;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView mView1;
    EditText mEdit1;
    EditText mEdit2;
    TextView mEventInfoTextView;
    View mView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView1 = findViewById(R.id.view1);
        mView2 = findViewById(R.id.view2);
        mEdit1 = findViewById(R.id.edit1);
        mEdit2 = findViewById(R.id.edit2);
        mEventInfoTextView = findViewById(R.id.event_info_text);

        //클릭
        setClickEvent();
        //포커스 변경
        setFocusEvent();
        //키
        setKeyEvent();
        //터치
        setTouchEvent();
    }

    private void setTouchEvent() {
        mView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.performClick();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(MainActivity.this, "터치다운", Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mEventInfoTextView.setText("터치 정보 : " + event.toString());
                        break;
                    case MotionEvent.ACTION_UP:
                        Toast.makeText(MainActivity.this, "터치 업", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }

    private void setKeyEvent() {
        View.OnKeyListener onKeyListenerTrue = (v, keyCode, event) -> {
            //뒤로가기 키 눌렀을 시,
            //뒤에 액션 체크를 안한다면 눌렀을때 땟을떄 두번 동작해버림
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                Toast.makeText(this, "뒤로가기가 눌렸당", Toast.LENGTH_SHORT).show();
            }
            return true;
        };
        View.OnKeyListener onKeyListenerFalse = (v, keyCode, event) -> {
            //뒤로가기 키 눌렀을 시,
            //뒤에 액션 체크를 안한다면 눌렀을때 땟을떄 두번 동작해버림
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                Toast.makeText(this, "뒤로가기가 눌렸당", Toast.LENGTH_SHORT).show();
            }
            return false;
        };
        //리턴 체크
        //리턴 트루일시 뒤로가기 키가 소비돼서 앱이 종료되지 않음
        //리턴 폴스일시 뒤로가기 키 이벤트가 흘러서 원래 뒤로가기키가 동작. 앱 종료됨
        mEdit1.setOnKeyListener(onKeyListenerTrue);
        mEdit2.setOnKeyListener(onKeyListenerFalse);
    }

    private void setFocusEvent() {
        View.OnFocusChangeListener onFocusChangeListener = (v, hasFocus) -> {
            //                                                 hasFocus 가 불린값으로 들어옴
            //true 일시 포커스 가짐, false 일시 포커스 없음
            if (hasFocus) {
                //v 는 포커스를 가진 View 객체.
                v.setBackgroundColor(Color.RED);
            } else {
                v.setBackgroundColor(Color.WHITE);
            }
        };
        //위에서 만든 걸
        mEdit1.setOnFocusChangeListener(onFocusChangeListener);
        mEdit2.setOnFocusChangeListener(onFocusChangeListener);
        //이렇게 달아줄 수 있음.
    }

    private void setClickEvent() {
        mView1.setOnClickListener(v -> {
            Toast.makeText(this, "클릭", Toast.LENGTH_SHORT).show();
        });
        mView1.setOnLongClickListener(v -> {
            Toast.makeText(this, "롱클릭", Toast.LENGTH_SHORT).show();
            //이벤트 소비
            return true;
            //false 로 설정시
            //클릭의 KEY_UP 까지 흘러가서 클릭 토스트도 출력됨.
        });
    }
}