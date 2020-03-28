package com.dc024.ptsgenap_11rpl1_absen11.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.dc024.ptsgenap_11rpl1_absen11.R;
import com.dc024.ptsgenap_11rpl1_absen11.model.rv_model;

import java.util.List;

public class rv_adapter extends RecyclerView.Adapter<rv_adapter.ScansDataViewHolder> {
    private List<rv_model> mList;
    private Context mCtx;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }


    public rv_adapter (Context ctx, List<rv_model> list) {
        this.mCtx = ctx;
        this.mList = list;
    }

    @NonNull
    @Override
    public ScansDataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_rv, viewGroup,false);
        return new ScansDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScansDataViewHolder accountsScansDataViewHolder, int position) {
        final rv_model aModel = mList.get(position);

        accountsScansDataViewHolder.tvNama.setText(aModel.getNama());
        accountsScansDataViewHolder.tvKode.setText(aModel.getKode());
        accountsScansDataViewHolder.tvJenis.setText(aModel.getJenis());
        accountsScansDataViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(aModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mList !=null? mList.size():0);
    }

    public class ScansDataViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama,tvJenis, tvKode;
        private EditText nama,jenis,text;



        public ScansDataViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = (TextView) itemView.findViewById(R.id.tvNama);
            tvJenis = (TextView) itemView.findViewById(R.id.tvJenis);
            tvKode = (TextView) itemView.findViewById(R.id.tvKode);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    final Dialog dialog = new Dialog(mCtx);
//                    dialog.setContentView(R.layout.dialog_detail);
//                    final EditText text = (EditText) dialog.findViewById(R.id.etKode);
//                    text.setText(mList.get(getAdapterPosition()).getKode());
//                    final EditText nama = (EditText) dialog.findViewById(R.id.etNama);
//                    nama.setText(mList.get(getAdapterPosition()).getNama());
//                    final EditText jenis = (EditText) dialog.findViewById(R.id.etJenis);
//                    jenis.setText(mList.get(getAdapterPosition()).getJenis());
//
//                    LinearLayout divSimpan = dialog.findViewById(R.id.divSave);
//                    divSimpan.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            HashMap<String, String> body = new HashMap<>();
//                            body.put("id", mList.get(getAdapterPosition()).getId());
//                            body.put("nama", nama.getText().toString());
//                            body.put("jenis", jenis.getText().toString());
//                            body.put("kode", text.getText().toString());
//                            AndroidNetworking.post("http://192.168.43.153/TugasPTS/api/update.php")
//                                    .addBodyParameter(body)
//                                    .setPriority(Priority.MEDIUM)
//                                    .build()
//                                    .getAsJSONObject(new JSONObjectRequestListener() {
//                                        @Override
//                                        public void onResponse(JSONObject response) {
//                                            Log.d("GZS", "respon : " + response);
//
//                                            String status = response.optString("STATUS");
//                                            String message = response.optString("MESSAGE");
//                                            if (status.equalsIgnoreCase("SUCCESS")) {
//                                                JSONObject payload = response.optJSONObject("PAYLOAD");
//                                                Toast.makeText(mCtx, message, Toast.LENGTH_SHORT).show();
//                                            }
//                                            else {
//                                                Toast.makeText(mCtx, message, Toast.LENGTH_SHORT).show();
//                                            }
//
//                                        }
//
//                                        @Override
//                                        public void onError(ANError anError) {
//                                            Toast.makeText(mCtx, "ERROR LUR", Toast.LENGTH_SHORT).show();
//                                            Log.d("GZS", "onError: " + anError.getErrorBody(    ));
//                                            Log.d("GZS", "onError: " + anError.getLocalizedMessage());
//                                            Log.d("GZS", "onError: " + anError.getErrorDetail());
//                                            Log.d("GZS", "onError: " + anError.getResponse());
//                                            Log.d("GZS  ", "onError: " + anError.getErrorCode());
//                                        }
//                                    });
//                        }
//                    });
//                    LinearLayout divHapus = dialog.findViewById(R.id.divDelete);
//                    divHapus.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            HashMap<String, String> body = new HashMap<>();
//                            body.put("id", mList.get(getAdapterPosition()).getId());
//                            AndroidNetworking.post("http://192.168.43.153/TugasPTS/api/delete.php")
//                                    .addBodyParameter(body)
//                                    .setPriority(Priority.MEDIUM)
//                                    .build()
//                                    .getAsJSONObject(new JSONObjectRequestListener() {
//                                        @Override
//                                        public void onResponse(JSONObject response) {
//                                            Log.d("GZS", "respon : " + response);
//
//                                            String status = response.optString("STATUS");
//                                            String message = response.optString("MESSAGE");
//                                            if (status.equalsIgnoreCase("SUCCESS")) {
//                                                JSONObject payload = response.optJSONObject("PAYLOAD");
//                                                Toast.makeText(mCtx, message, Toast.LENGTH_SHORT).show();
//                                            }
//                                            else {
//                                                Toast.makeText(mCtx, message, Toast.LENGTH_SHORT).show();
//                                            }
//
//                                        }
//
//                                        @Override
//                                        public void onError(ANError anError) {
//                                            Toast.makeText(mCtx, "ERROR LUR", Toast.LENGTH_SHORT).show();
//                                            Log.d("GZS", "onError: " + anError.getErrorBody(    ));
//                                            Log.d("GZS", "onError: " + anError.getLocalizedMessage());
//                                            Log.d("GZS", "onError: " + anError.getErrorDetail());
//                                            Log.d("GZS", "onError: " + anError.getResponse());
//                                            Log.d("GZS  ", "onError: " + anError.getErrorCode());
//                                        }
//                                    });
//                        }
//                    });
//
//
//
//
//
//                    dialog.show();

//
//                }
//            });


        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(rv_model data);
    }
}

