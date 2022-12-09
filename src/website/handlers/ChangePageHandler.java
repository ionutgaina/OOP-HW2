package website.handlers;

import website.CurrentPage;
import website.Invoker;

import java.util.Arrays;

public class ChangePageHandler {
//    private void changePage(String page) {
//        CurrentPage currentPage = CurrentPage.getInstance();
//        boolean resultCurrentPage;
//
//        resultCurrentPage = Arrays
//                .stream(Invoker.unLoggedEnum.values())
//                .map(Invoker.unLoggedEnum::getPage)
//                .anyMatch(currentPage.getCurrentPage()::contains);
//
//        if (resultCurrentPage) {
//            switch (page) {
//                case "login", "register" -> {
//                    System.out.println("nelogat");
//                    acceptChangePage(page);
//                    return;
//                }
//                default -> {
//                    System.out.println("eroare nelogat");
//                    // error
//                    return;
//                }
//            }
//        }
//
//        resultCurrentPage = Arrays.stream(Invoker.loggedEnum.values())
//                .map(Invoker.loggedEnum::getPage)
//                .anyMatch(currentPage.getCurrentPage()::contains);
//
//        if (resultCurrentPage) {
//            switch (page) {
//                case "upgrades":
//                case "movies":
//                    System.out.println("logat");
//                    acceptChangePage(page);
//                    return;
//                case "logout":
//                    System.out.println("ma deloghez");
//                    acceptChangePage(page);
//                    return;
//
//                case "see details":
//                    if (currentPage.getCurrentPage().equals("see details"))
//                    {
//                        //error
//                        System.out.println("details error");
//                        return;
//                    }
//
//                    return;
//                default:
//                    // error
//                    System.out.println("eroare logat");
//            }
//        }
//
//        // error
//    }

}
