function [ output_args ] = primefactors( input_args )

primes = [2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97]
current_output_arg = 1
rest = input_args

while ~any(rest==primes)
for prime = primes
    if prime <= rest & mod(rest, prime) == 0
        rest = rest / prime
        output_args(current_output_arg) = prime
        current_output_arg = current_output_arg + 1
    end
end

end

