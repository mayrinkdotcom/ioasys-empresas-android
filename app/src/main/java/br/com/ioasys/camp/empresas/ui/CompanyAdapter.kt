package br.com.ioasys.camp.empresas.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import br.com.ioasys.camp.empresas.Company
import br.com.ioasys.camp.empresas.R
import com.bumptech.glide.Glide

class CompanyAdapter(private val callback: (Company) -> Unit): RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>(){

    private var companies = emptyList<Company>()

    fun setItems(list: List<Company>) {
        companies = list
        notifyDataSetChanged()
    }

    inner class CompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivImageCompany: AppCompatImageView = itemView.findViewById<AppCompatImageView>(R.id.imageCompany)
        private val tvCompanyName: AppCompatTextView = itemView.findViewById<AppCompatTextView>(R.id.textCompanyName)
        private val tvCompanyRole: AppCompatTextView = itemView.findViewById<AppCompatTextView>(R.id.textCompanyRole)
        private val tvCompanyCountry: AppCompatTextView = itemView.findViewById<AppCompatTextView>(R.id.textCompanyCountry)

        fun bind(company: Company) {
            tvCompanyName.text = company.companyName
            tvCompanyRole.text = company.companyType.companyTypeName
            tvCompanyCountry.text = company.country
            itemView.setOnClickListener { callback.invoke(company) }

            Glide
                .with(itemView)
                .load(company.pathImage)
                .placeholder(R.drawable.toolbar_background)
                .into(ivImageCompany)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_company, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bind(companies[position])
    }

    override fun getItemCount() = companies.size
}
