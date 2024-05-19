

package com.example.teachandlearn.Student.Form2.Documents;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.teachandlearn.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;
import java.util.List;


public class Form2PDF extends AppCompatActivity {
    private RecyclerView recyclerViewPDFs;
    private PDFAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_pdf);

        recyclerViewPDFs = findViewById(R.id.recyclerViewPDFs);
        recyclerViewPDFs.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PDFAdapter(new ArrayList<>(), this);
        recyclerViewPDFs.setAdapter(adapter);

        fetchPDFsFromFirebase();
    }

    private void fetchPDFsFromFirebase() {
        // Get a reference to the Firebase storage location

        FirebaseStorage storage = FirebaseStorage.getInstance();


        StorageReference storageRef1 = storage.getReference().child("/form2/sciences/mathematics/pdfs/");

        StorageReference storageRef2 = storage.getReference().child("/form2/sciences/biology/pdfs/");

        StorageReference storageRef3 = storage.getReference().child("/form2/sciences/agriculture/pdfs/");

        StorageReference storageRef4 = storage.getReference().child("/form2/sciences/chemistry/pdfs/");

        StorageReference storageRef5 = storage.getReference().child("/form2/sciences/physics/pdfs/");

        StorageReference storageRef6 = storage.getReference().child("/form2/languages/english/pdfs/");

        StorageReference storageRef7 = storage.getReference().child("/form2/languages/chichewa/pdfs/");

        StorageReference storageRef8 = storage.getReference().child("/form2/humanities/social_studies/pdfs/");

        StorageReference storageRef9 = storage.getReference().child("/form2/humanities/history/pdfs/");

        StorageReference storageRef10 = storage.getReference().child("/form2/humanities/life_skills/pdfs/");

        StorageReference storageRef11 = storage.getReference().child("/form2/humanities/bible_knowledge/pdfs/");

        StorageReference storageRef12 = storage.getReference().child("/form2/humanities/geography/pdfs/");






        storageRef1.listAll().addOnSuccessListener(listResult -> {
            List<PDFDocument> pdfs = new ArrayList<>();
            // Iterate through each item (PDF) in the storage location
            for (StorageReference item : listResult.getItems()) {
                // Get the download URL for the PDF
                item.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Add the PDF with its download URL to the list
                    pdfs.add(new PDFDocument(item.getName(), uri.toString()));
                    // Update the adapter with the new list of PDFs
                    adapter.setPDFDocuments(pdfs);
                }).addOnFailureListener(exception -> {
                    // Handle any errors
                    Log.e("PDF", "Failed to get download URL for PDF", exception);
                });
            }
            // If no PDFs were found, display "NO file Uploaded"
            if (pdfs.isEmpty()) {
                showNoFilesUploaded();
            }
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.e("PDF", "Failed to list PDF files", exception);
            // Show "NO file Uploaded" in case of failure as well
            showNoFilesUploaded();
        });

        storageRef2.listAll().addOnSuccessListener(listResult -> {
            List<PDFDocument> pdfs = new ArrayList<>();
            // Iterate through each item (PDF) in the storage location
            for (StorageReference item : listResult.getItems()) {
                // Get the download URL for the PDF
                item.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Add the PDF with its download URL to the list
                    pdfs.add(new PDFDocument(item.getName(), uri.toString()));
                    // Update the adapter with the new list of PDFs
                    adapter.setPDFDocuments(pdfs);
                }).addOnFailureListener(exception -> {
                    // Handle any errors
                    Log.e("PDF", "Failed to get download URL for PDF", exception);
                });
            }
            // If no PDFs were found, display "NO file Uploaded"
            if (pdfs.isEmpty()) {
                showNoFilesUploaded();
            }
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.e("PDF", "Failed to list PDF files", exception);
            // Show "NO file Uploaded" in case of failure as well
            showNoFilesUploaded();
        });

        storageRef3.listAll().addOnSuccessListener(listResult -> {
            List<PDFDocument> pdfs = new ArrayList<>();
            // Iterate through each item (PDF) in the storage location
            for (StorageReference item : listResult.getItems()) {
                // Get the download URL for the PDF
                item.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Add the PDF with its download URL to the list
                    pdfs.add(new PDFDocument(item.getName(), uri.toString()));
                    // Update the adapter with the new list of PDFs
                    adapter.setPDFDocuments(pdfs);
                }).addOnFailureListener(exception -> {
                    // Handle any errors
                    Log.e("PDF", "Failed to get download URL for PDF", exception);
                });
            }
            // If no PDFs were found, display "NO file Uploaded"
            if (pdfs.isEmpty()) {
                showNoFilesUploaded();
            }
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.e("PDF", "Failed to list PDF files", exception);
            // Show "NO file Uploaded" in case of failure as well
            showNoFilesUploaded();
        });


        storageRef4.listAll().addOnSuccessListener(listResult -> {
            List<PDFDocument> pdfs = new ArrayList<>();
            // Iterate through each item (PDF) in the storage location
            for (StorageReference item : listResult.getItems()) {
                // Get the download URL for the PDF
                item.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Add the PDF with its download URL to the list
                    pdfs.add(new PDFDocument(item.getName(), uri.toString()));
                    // Update the adapter with the new list of PDFs
                    adapter.setPDFDocuments(pdfs);
                }).addOnFailureListener(exception -> {
                    // Handle any errors
                    Log.e("PDF", "Failed to get download URL for PDF", exception);
                });
            }
            // If no PDFs were found, display "NO file Uploaded"
            if (pdfs.isEmpty()) {
                showNoFilesUploaded();
            }
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.e("PDF", "Failed to list PDF files", exception);
            // Show "NO file Uploaded" in case of failure as well
            showNoFilesUploaded();
        });



        storageRef5.listAll().addOnSuccessListener(listResult -> {
            List<PDFDocument> pdfs = new ArrayList<>();
            // Iterate through each item (PDF) in the storage location
            for (StorageReference item : listResult.getItems()) {
                // Get the download URL for the PDF
                item.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Add the PDF with its download URL to the list
                    pdfs.add(new PDFDocument(item.getName(), uri.toString()));
                    // Update the adapter with the new list of PDFs
                    adapter.setPDFDocuments(pdfs);
                }).addOnFailureListener(exception -> {
                    // Handle any errors
                    Log.e("PDF", "Failed to get download URL for PDF", exception);
                });
            }
            // If no PDFs were found, display "NO file Uploaded"
            if (pdfs.isEmpty()) {
                showNoFilesUploaded();
            }
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.e("PDF", "Failed to list PDF files", exception);
            // Show "NO file Uploaded" in case of failure as well
            showNoFilesUploaded();
        });

        storageRef6.listAll().addOnSuccessListener(listResult -> {
            List<PDFDocument> pdfs = new ArrayList<>();
            // Iterate through each item (PDF) in the storage location
            for (StorageReference item : listResult.getItems()) {
                // Get the download URL for the PDF
                item.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Add the PDF with its download URL to the list
                    pdfs.add(new PDFDocument(item.getName(), uri.toString()));
                    // Update the adapter with the new list of PDFs
                    adapter.setPDFDocuments(pdfs);
                }).addOnFailureListener(exception -> {
                    // Handle any errors
                    Log.e("PDF", "Failed to get download URL for PDF", exception);
                });
            }
            // If no PDFs were found, display "NO file Uploaded"
            if (pdfs.isEmpty()) {
                showNoFilesUploaded();
            }
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.e("PDF", "Failed to list PDF files", exception);
            // Show "NO file Uploaded" in case of failure as well
            showNoFilesUploaded();
        });

        storageRef7.listAll().addOnSuccessListener(listResult -> {
            List<PDFDocument> pdfs = new ArrayList<>();
            // Iterate through each item (PDF) in the storage location
            for (StorageReference item : listResult.getItems()) {
                // Get the download URL for the PDF
                item.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Add the PDF with its download URL to the list
                    pdfs.add(new PDFDocument(item.getName(), uri.toString()));
                    // Update the adapter with the new list of PDFs
                    adapter.setPDFDocuments(pdfs);
                }).addOnFailureListener(exception -> {
                    // Handle any errors
                    Log.e("PDF", "Failed to get download URL for PDF", exception);
                });
            }
            // If no PDFs were found, display "NO file Uploaded"
            if (pdfs.isEmpty()) {
                showNoFilesUploaded();
            }
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.e("PDF", "Failed to list PDF files", exception);
            // Show "NO file Uploaded" in case of failure as well
            showNoFilesUploaded();
        });


        storageRef8.listAll().addOnSuccessListener(listResult -> {
            List<PDFDocument> pdfs = new ArrayList<>();
            // Iterate through each item (PDF) in the storage location
            for (StorageReference item : listResult.getItems()) {
                // Get the download URL for the PDF
                item.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Add the PDF with its download URL to the list
                    pdfs.add(new PDFDocument(item.getName(), uri.toString()));
                    // Update the adapter with the new list of PDFs
                    adapter.setPDFDocuments(pdfs);
                }).addOnFailureListener(exception -> {
                    // Handle any errors
                    Log.e("PDF", "Failed to get download URL for PDF", exception);
                });
            }
            // If no PDFs were found, display "NO file Uploaded"
            if (pdfs.isEmpty()) {
                showNoFilesUploaded();
            }
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.e("PDF", "Failed to list PDF files", exception);
            // Show "NO file Uploaded" in case of failure as well
            showNoFilesUploaded();
        });


        storageRef9.listAll().addOnSuccessListener(listResult -> {
            List<PDFDocument> pdfs = new ArrayList<>();
            // Iterate through each item (PDF) in the storage location
            for (StorageReference item : listResult.getItems()) {
                // Get the download URL for the PDF
                item.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Add the PDF with its download URL to the list
                    pdfs.add(new PDFDocument(item.getName(), uri.toString()));
                    // Update the adapter with the new list of PDFs
                    adapter.setPDFDocuments(pdfs);
                }).addOnFailureListener(exception -> {
                    // Handle any errors
                    Log.e("PDF", "Failed to get download URL for PDF", exception);
                });
            }
            // If no PDFs were found, display "NO file Uploaded"
            if (pdfs.isEmpty()) {
                showNoFilesUploaded();
            }
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.e("PDF", "Failed to list PDF files", exception);
            // Show "NO file Uploaded" in case of failure as well
            showNoFilesUploaded();
        });

        storageRef10.listAll().addOnSuccessListener(listResult -> {
            List<PDFDocument> pdfs = new ArrayList<>();
            // Iterate through each item (PDF) in the storage location
            for (StorageReference item : listResult.getItems()) {
                // Get the download URL for the PDF
                item.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Add the PDF with its download URL to the list
                    pdfs.add(new PDFDocument(item.getName(), uri.toString()));
                    // Update the adapter with the new list of PDFs
                    adapter.setPDFDocuments(pdfs);
                }).addOnFailureListener(exception -> {
                    // Handle any errors
                    Log.e("PDF", "Failed to get download URL for PDF", exception);
                });
            }
            // If no PDFs were found, display "NO file Uploaded"
            if (pdfs.isEmpty()) {
                showNoFilesUploaded();
            }
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.e("PDF", "Failed to list PDF files", exception);
            // Show "NO file Uploaded" in case of failure as well
            showNoFilesUploaded();
        });

        storageRef11.listAll().addOnSuccessListener(listResult -> {
            List<PDFDocument> pdfs = new ArrayList<>();
            // Iterate through each item (PDF) in the storage location
            for (StorageReference item : listResult.getItems()) {
                // Get the download URL for the PDF
                item.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Add the PDF with its download URL to the list
                    pdfs.add(new PDFDocument(item.getName(), uri.toString()));
                    // Update the adapter with the new list of PDFs
                    adapter.setPDFDocuments(pdfs);
                }).addOnFailureListener(exception -> {
                    // Handle any errors
                    Log.e("PDF", "Failed to get download URL for PDF", exception);
                });
            }
            // If no PDFs were found, display "NO file Uploaded"
            if (pdfs.isEmpty()) {
                showNoFilesUploaded();
            }
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.e("PDF", "Failed to list PDF files", exception);
            // Show "NO file Uploaded" in case of failure as well
            showNoFilesUploaded();
        });


        storageRef12.listAll().addOnSuccessListener(listResult -> {
            List<PDFDocument> pdfs = new ArrayList<>();
            // Iterate through each item (PDF) in the storage location
            for (StorageReference item : listResult.getItems()) {
                // Get the download URL for the PDF
                item.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Add the PDF with its download URL to the list
                    pdfs.add(new PDFDocument(item.getName(), uri.toString()));
                    // Update the adapter with the new list of PDFs
                    adapter.setPDFDocuments(pdfs);
                }).addOnFailureListener(exception -> {
                    // Handle any errors
                    Log.e("PDF", "Failed to get download URL for PDF", exception);
                });
            }
            // If no PDFs were found, display "NO file Uploaded"
            if (pdfs.isEmpty()) {
                showNoFilesUploaded();
            }
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.e("PDF", "Failed to list PDF files", exception);
            // Show "NO file Uploaded" in case of failure as well
            showNoFilesUploaded();
        });





    }

    private void showNoFilesUploaded() {
        // Clear the existing list of PDFs
        adapter.setPDFDocuments(new ArrayList<>());
        // Display "NO file Uploaded" message
        Toast.makeText(this, "No file Uploaded", Toast.LENGTH_SHORT).show();
    }


    public static class PDFDocument {
        private String title;
        private String downloadUrl;

        public PDFDocument() {
        }

        public PDFDocument(String title, String downloadUrl) {
            this.title = title;
            this.downloadUrl = downloadUrl;
        }

        public String getTitle() {
            return title;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }
    }

    private static class PDFAdapter extends RecyclerView.Adapter<PDFAdapter.PDFViewHolder> {
        private List<PDFDocument> pdfDocuments;
        private Context context;

        public PDFAdapter(List<PDFDocument> pdfDocuments, Context context) {
            this.pdfDocuments = pdfDocuments;
            this.context = context;
        }

        @Override
        public PDFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_form1_pdf_item, parent, false);
            return new PDFViewHolder(view);
        }

        @Override
        public void onBindViewHolder(PDFViewHolder holder, int position) {
            PDFDocument document = pdfDocuments.get(position);
            holder.textViewTitle.setText(document.getTitle());
            holder.itemView.setOnClickListener(v -> {
                // Download and view the PDF when the item is clicked
                downloadAndOpenPDF(document.getDownloadUrl());
            });
        }

        private void downloadAndOpenPDF(String downloadUrl) {
            // Create an Intent to view the PDF
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(downloadUrl), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Check if there's any app available to handle the Intent
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            } else {
                // If no app is available to handle the Intent, show a toast
                Toast.makeText(context, "No application found to open this file.", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public int getItemCount() {
            return pdfDocuments.size();
        }

        public void setPDFDocuments(List<PDFDocument> pdfDocuments) {
            this.pdfDocuments = pdfDocuments;
            notifyDataSetChanged(); // Notify the adapter that the data set has changed
        }

        public static class PDFViewHolder extends RecyclerView.ViewHolder {
            TextView textViewTitle;

            public PDFViewHolder(View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.textViewTitle);
            }
        }
    }
}
