package com.example.reviewmaster;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CutomeAdpter extends RecyclerView.Adapter<CutomeAdpter.ViewHolder>
{

    private ArrayList<chickenItem> mchickenItems;
    private Context mContext;
    private DBHelper mDBHelper;

    public CutomeAdpter(ArrayList<chickenItem> mchickenItems, Context mContext) {
        this.mchickenItems = mchickenItems;
        this.mContext = mContext;
        mDBHelper = new DBHelper(mContext);
    }

    @NonNull
    @Override
    public CutomeAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull CutomeAdpter.ViewHolder holder, int position) {
        holder.tv_id.setText(mchickenItems.get(position).getId());
        holder.tv_st.setText(mchickenItems.get(position).getStore());
        holder.tv_ki.setText(mchickenItems.get(position).getKind());
        holder.tv_re.setText(mchickenItems.get(position).getRe_view());
        holder.tv_date.setText(mchickenItems.get(position).getWriteDate());
    }

    @Override
    public int getItemCount() {
        return mchickenItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_id;
        private TextView tv_st;
        private TextView tv_ki;
        private TextView tv_re;
        private TextView tv_date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.tv_id);
            tv_st = itemView.findViewById(R.id.tv_st);
            tv_ki = itemView.findViewById(R.id.tv_ki);
            tv_re = itemView.findViewById(R.id.tv_re);
            tv_date= itemView.findViewById(R.id.tv_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int curPos = getAdapterPosition(); //현재 리스트 아이템 위치
                    chickenItem chickenItem = mchickenItems.get(curPos);

                    String[] strChoiceItems = {"수정하기", "삭제하기"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("원하는 작업을 선택 해주세요");
                    builder.setItems(strChoiceItems, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int position) {
                            if(position == 0){
                                //수정
                                Dialog dialog = new Dialog(mContext, android.R.style.Theme_Material_Light_Dialog);
                                dialog.setContentView(R.layout.dialog_edit);
                                EditText et_desc_id = dialog.findViewById(R.id.et_desc_id);
                                EditText et_desc_st = dialog.findViewById(R.id.et_desc_st);
                                EditText et_desc_ki = dialog.findViewById(R.id.et_desc_ki);
                                EditText et_desc_re = dialog.findViewById(R.id.et_desc_re);
                                Button button = dialog.findViewById((R.id.button));

                                et_desc_id.setText(chickenItem.getId());
                                et_desc_st.setText(chickenItem.getStore());
                                et_desc_ki.setText(chickenItem.getKind());
                                et_desc_re.setText(chickenItem.getRe_view());

                                et_desc_id.setSelection(et_desc_id.getText().length());



                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String id = et_desc_id.getText().toString();
                                        String store = et_desc_st.getText().toString();
                                        String kind = et_desc_ki.getText().toString();
                                        String re_view = et_desc_re.getText().toString();
                                        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                        String beforeTime = chickenItem.getWriteDate();

                                        mDBHelper.UpdateTodo(id, store, kind, re_view, currentTime, beforeTime);

                                        //update ui
                                        chickenItem.setId(id);
                                        chickenItem.setStore(store);
                                        chickenItem.setKind(kind);
                                        chickenItem.setRe_view(re_view);
                                        chickenItem.setWriteDate(currentTime);
                                        notifyItemChanged(curPos, chickenItem);
                                        dialog.dismiss();
                                        Toast.makeText(mContext, "목록 수정이 완료 되었습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                dialog.show();
                            }
                            else if(position == 1){
                                //삭제
                                String beforeTime = chickenItem.getWriteDate();
                                mDBHelper.deleteTodo(beforeTime);

                                //삭제 ui
                                mchickenItems.remove(curPos);
                                notifyItemRemoved(curPos);
                                Toast.makeText(mContext, "목록이 제거 되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.show();
                }
            });
        }
    }

    //엑티비티에서 호출되는 함수이며, 현재 어댑터에 새로운 게시글 아이템을 전달받아 추가하는 목적이다.
    public void addItem(chickenItem _item) {
        mchickenItems.add(0, _item);
        notifyItemInserted(0);
    }

}
