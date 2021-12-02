package com.example.task.service;

        import com.example.task.entity.*;
        import com.example.task.payloat.*;
        import com.example.task.repository.*;
        import com.example.task.utils.CommonUtils;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.sql.Date;
        import java.util.*;
        import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DetailRepository detailRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    public ApiResponseOrder addOrder(ReqOrder reqOrder){
        ApiResponseOrder apiResponseModel=new ApiResponseOrder();
        try {
            Optional<Customer> customerOptional=customerRepository.findById(reqOrder.getCustomer_id());
            if (customerOptional.isPresent()){
                Order order=new Order();
                order.setCust(customerOptional.get());
                orderRepository.save(order);

                Optional<Product> productOptional=productRepository.findById(reqOrder.getProduct_id());
                if (productOptional.isPresent()){
                    Detail detail=new Detail();
                    detail.setOrd(order);
                    detail.setPr(productOptional.get());
                    detail.setQuantity(reqOrder.getQuantity());
                    detailRepository.save(detail);

                    Invoice invoice=new Invoice();
                    invoice.setOrd(order);
                    invoiceRepository.save(invoice);

                    apiResponseModel.setStatus("SUCCESS");
                    apiResponseModel.setInvoice_number(invoice.getId());
                }
                else {
                    apiResponseModel.setStatus("Product did not found");
                    return apiResponseModel;
                }
            }
            else {
                apiResponseModel.setStatus("Customer did not found");
                return apiResponseModel;
            }
        }
        catch (Exception e){
            apiResponseModel.setStatus("FAILED");
        }
        return apiResponseModel;
    }

    public ApiResponsGetOrder getOrder(Integer order_id){
        ApiResponsGetOrder apiResponsGetOrder=new ApiResponsGetOrder();
        try{
            Optional<Order> optionalOrder=orderRepository.findById(order_id);
            if (optionalOrder.isPresent()){
                List<Detail> detailOptional=detailRepository.findAllByOrd(optionalOrder.get());
                if (detailOptional!=null){

                    List<String> productName=detailOptional.stream().map(detail -> detail.getPr().getName()).collect(Collectors.toList());
                    apiResponsGetOrder.setProduct_name(productName);
                    apiResponsGetOrder.setOrder(optionalOrder.get());
                    apiResponsGetOrder.setMessage("SUCCESS");
                    apiResponsGetOrder.setCode(200);
                }
                else {
                    apiResponsGetOrder.setCode(207);
                    apiResponsGetOrder.setMessage("Detail did not found");
                    return apiResponsGetOrder;
                }
            }
            else {
                apiResponsGetOrder.setMessage("Order did not found");
                apiResponsGetOrder.setCode(207);
                return apiResponsGetOrder;
            }

        }
        catch (Exception exception){
            apiResponsGetOrder.setCode(500);
            apiResponsGetOrder.setMessage("Error");
        }
        return apiResponsGetOrder;
    }


    public ApiResponseModel getOrderWithoutDetails(){
        ApiResponseModel apiResponseModel=new ApiResponseModel();
        try {

            List<Order> orderList=orderRepository.findAll().stream().map(order ->getOrderDetail(order, CommonUtils.parseTimestamp("2016-09-06")) ).collect(Collectors.toList());
            apiResponseModel.setMessage("SUCCESS");
            apiResponseModel.setCode(200);
            orderList.removeAll(Collections.singletonList(null));
            apiResponseModel.setObject(orderList);
        }
        catch (Exception exception){
            apiResponseModel.setCode(500);
            apiResponseModel.setMessage("ERROR");
        }
        return apiResponseModel;
    }
    public Order getOrderDetail(Order order,Date dateOrder)  {
        if (order.getDate().before(dateOrder) && !detailRepository.existsByOrd(order)){
            return order;
        }
        else return null;
    }



    public ApiResponseModel getHighDemandProducts(){
    ApiResponseModel apiResponseModel=new ApiResponseModel();
    try {
          List<ResHighDemandProduct> resHighDemandProductList=productRepository.findAll().stream().map(product ->getHDPoducut(product)).collect(Collectors.toList());
          resHighDemandProductList.removeAll(Collections.singletonList(null));
          apiResponseModel.setMessage("Success");
          apiResponseModel.setCode(200);
          apiResponseModel.setObject(resHighDemandProductList);
    }
    catch (Exception exception){
        apiResponseModel.setCode(500);
        apiResponseModel.setMessage("Error");
    }
    return apiResponseModel;
    }

    public ResHighDemandProduct getHDPoducut(Product product){
        List<Detail> detailList=detailRepository.findAllByPr(product);
        if (detailList.size()==0){
            return null;
        }
        else {
            Integer quantity=0;
            for (Detail detail:detailList) {
                quantity+=detail.getQuantity();
            }
            if (quantity>=10){
                ResHighDemandProduct resHighDemandProduct=new ResHighDemandProduct();
                resHighDemandProduct.setProduct_Id(product.getId());
                resHighDemandProduct.setQuantity(quantity);
                return resHighDemandProduct;
            }
            else return null;
        }
    }

    public ApiResponseModel getBulkProduct(){
        ApiResponseModel apiResponseModel=new ApiResponseModel();
        try {
             List<ResBulkProduct> resBulkProducts=productRepository.findAll().stream().map(product -> getBulkPr(product)).collect(Collectors.toList());
             resBulkProducts.removeAll(Collections.singletonList(null));
             apiResponseModel.setCode(200);
             apiResponseModel.setMessage("Success");
             apiResponseModel.setObject(resBulkProducts);
        }
        catch (Exception exception){
            apiResponseModel.setMessage("Error");
            apiResponseModel.setCode(500);
        }
        return apiResponseModel;
    }

    public ResBulkProduct getBulkPr(Product product){
        List<Detail> detailList=detailRepository.findAllByPr(product);
        if (detailList.size()==0){
            return null;
        }
        else {
            Integer quantity=0;
            for (Detail detail:detailList) {
                if (detail.getQuantity()>=8)
                    quantity++;
            }
            if (quantity==detailList.size()){
                ResBulkProduct resBulkProduct=new ResBulkProduct();
                resBulkProduct.setProduct_Id(product.getId());
                resBulkProduct.setPrice(product.getPrice());
                return resBulkProduct;
            }
            else return null;
        }
    }

    public ApiResponseModel getNumberProductYear(){

//        List<Order> orderList22=orderRepository.findAll().stream().sorted(Comparator.comparing(Order::getDate)).collect(Collectors.toList());
//        orderList22.forEach(System.out::println);
        ApiResponseModel apiResponseModel=new ApiResponseModel();
        try {
            countryName.clear();
            List<ResNumProductYear> resNumProductYears =orderRepository.findAll().stream().map(order -> getNPYear(order)).collect(Collectors.toList());
            resNumProductYears.removeAll(Collections.singletonList(null));
            apiResponseModel.setMessage("Success");
            apiResponseModel.setCode(200);
            apiResponseModel.setObject(resNumProductYears);


        }
        catch (Exception exception ){
            apiResponseModel.setCode(500);
            apiResponseModel.setMessage("Error");
        }
        return apiResponseModel;

    }

    public ResNumProductYear getNPYear(Order order){

            if (!countryName.contains(order.getCust().getCountry())){
            countryName.add(order.getCust().getCountry());
            List<Order> list= orderRepository.findAllByCust_Country(order.getCust().getCountry()).stream().map(order1 -> getDateAfter(order1,CommonUtils.parseTimestamp("2015-12-31"),CommonUtils.parseTimestamp("2017-01-01"))).collect(Collectors.toList());
            list.removeAll(Collections.singletonList(null));
            if (list.size()!=0) {
                ResNumProductYear resNumProductYear = new ResNumProductYear();
                resNumProductYear.setNumber((long) list.size());
                resNumProductYear.setCountry(order.getCust().getCountry());

                return resNumProductYear;
            }
            else return null;
            }
            else return null;
    }
    public Order getDateAfter(Order order,Date dateBefore, Date dateAfter){
        if (order.getDate().after(dateBefore) && order.getDate().before(dateAfter)){
         return order;
        }
        else return null;
    }
    ArrayList<String> countryName=new ArrayList<>();

    public ApiResponseModel getOrdersWithoutInvoices(){
        ApiResponseModel apiResponseModel=new ApiResponseModel();
        try {
            List<ResOrdersWithoutInvoices> orderList=detailRepository.findAll().stream().map(detail ->getOWInvoices(detail)).collect(Collectors.toList());
             orderList.removeAll(Collections.singletonList(null));
             apiResponseModel.setMessage("Success");
             apiResponseModel.setCode(200);
             apiResponseModel.setObject(orderList);
        }
        catch (Exception exception){
            apiResponseModel.setCode(500);
            apiResponseModel.setMessage("Error");
        }
        return apiResponseModel;
    }
    public ResOrdersWithoutInvoices getOWInvoices(Detail detail){
        if (!invoiceRepository.existsByOrd(detail.getOrd()) && detail.getOrd()!=null){
            ResOrdersWithoutInvoices resOrdersWithoutInvoices=new ResOrdersWithoutInvoices();
            resOrdersWithoutInvoices.setDate(detail.getOrd().getDate());
            resOrdersWithoutInvoices.setPrice(detail.getPr().getPrice()*detail.getQuantity());
            resOrdersWithoutInvoices.setOrder_Id(detail.getOrd().getId());
             return  resOrdersWithoutInvoices;
        }
        else return null;
    }
}
