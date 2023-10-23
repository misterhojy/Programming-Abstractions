(*Hw 1 for 216*)

(* Helper function similar to List.Map*)
let rec map f lst = match lst with 
| [] -> []
| h::t -> (f h) :: (map f t)

(* 1 *)
let rec pow x n = 
  if n = 0 then
    1
  else
    x * pow x (n-1);;

(*part b*)
let rec float_pow x n =
  if n = 0 then
    1.0
  else
    x *. float_pow x (n - 1);;

(* 2 *)
    let rec compress =  function
| a :: (b :: _ as t) -> if a = b then compress t else a :: compress t
| list -> list;;

  (* 3 *)
let rec remove_if lst f = match lst with
|[] -> []
|h :: t -> 
  if (f h) then
    remove_if t f
  else 
    h :: remove_if t f;;

(* 4 *)
let slice list i j =
  let rec get n = function
| [] -> []
| h :: t -> if i > j then [] else if n = 0 then [] else h :: get(n-1) t in
  let rec remv n = function
| [] -> []
| h :: t as l -> if n = 0 then l else remv (n - 1) t in
  get(j - i) (remv i list);;

(* 5 *)
(*going to take function element and list, will know what group the element goes in*)
let rec add_to_end lst x = match lst with
| [] -> [x]
| h :: t -> h :: (add_to_end t x)

let rec add_to_groups f x equivs = match equivs with
| [] -> [[x]]
| h :: t ->
    if f x (List.hd h) then (add_to_end h x) :: t
    else h :: add_to_groups f x t

let equivs f lst =
  let rec aux f lst acc = match lst with
  | [] ->  acc
  | hd :: tl ->
      let new_acc = add_to_groups f hd acc in
      aux f tl new_acc
  in
  aux f lst []

(* 6 *)
let is_prime n =
  let rec check_divisor d =
    d * d > n || (n mod d <> 0 && check_divisor (d + 1))
  in n > 1 && check_divisor 2

let goldbachpair n =
  let rec check_primes i =
    if i > n / 2 then failwith "No prime pair found"
    else if is_prime i && is_prime (n - i) then (i, n - i)
    else check_primes (i + 1)
  in if n <= 2 || n mod 2 = 1 then failwith "Input must be even and greater than 2"
     else check_primes 2


(* 7 *)
let rec identical_on f g lst = match lst with 
|[] -> true
| h :: t -> if (f h) = (g h) then identical_on f g t else false;;

(* 8 *)
let rec pairwisefilter cmp lst = match lst with
|[] -> []
|h :: num2 :: t -> let tt = pairwisefilter cmp t in (cmp h num2) :: tt
|h :: [] -> [h];;


(* 9 *)
let rec polynomial lst x = match lst with
| [] -> 0
| (c,e) :: t -> let remain = polynomial t x in 
let y = int_of_float((float_of_int x) ** (float_of_int e)) in 
 (c * y) + remain;;

(* 10 *)
let rec powerset lst = match lst with 
| [] -> [[]] 
| h :: t -> let ps = powerset t in ps @ map(fun subset -> h :: subset) ps

